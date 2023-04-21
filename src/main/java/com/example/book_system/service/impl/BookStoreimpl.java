package com.example.book_system.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.book_system.entity.BookStore;
import com.example.book_system.repository.BookStoreDao;
import com.example.book_system.service.ifs.BookStoreService;
import com.example.book_system.vo.BookStoreRequest;
import com.example.book_system.vo.BookStoreRequest2;
import com.example.book_system.vo.BookStoreResponse;
import com.example.book_system.vo.ReadBookResponse;

@Service
public class BookStoreimpl implements BookStoreService {
	@Autowired
	private BookStoreDao bookStoreDao;

	@Override
	// 新增書籍
	public BookStoreResponse addBook(BookStoreRequest bookStoreRequest) {

//創建一個空的List，用於保存新增書籍時重複的信息。
		List<BookStore> errorInfoList = new ArrayList<>();
//從傳入的參數中，獲取要新增的書籍信息。
		List<BookStore> BookStoreinfoList = bookStoreRequest.getBookStorelist();
//遍歷書籍信息列表。
		for (BookStore item : BookStoreinfoList) {
//如果書名為空，則返回一個錯誤信息。
			if (!StringUtils.hasText(item.getBooktitle())) {
				// 創建一個BookStoreResponse對象，返回錯誤信息和空的書籍列表。
				return new BookStoreResponse(BookStoreinfoList, "欄位不得為空");
			}
			// 檢查書籍是否已經存在於數據庫中。
			if (bookStoreDao.existsById(item.getBooktitle())) {
//如果書籍已存在，則把該書籍信息添加到errorInfoList中。
				errorInfoList.add(item);

			}

		}
		// 如果errorInfoList不為空，則返回重複信息和重複的書籍列表。
		if (!errorInfoList.isEmpty()) {
			// 創建一個BookStoreResponse對象，返回重複信息和重複的書籍列表。
			return new BookStoreResponse(errorInfoList, "本書已新增過");
		}
		// 保存所有新增的書籍信息。
		bookStoreDao.saveAll(BookStoreinfoList);
//創建一個BookStoreResponse對象，返回新增成功的書籍列表和成功信息。
		return new BookStoreResponse(BookStoreinfoList, "新增書籍成功");
	}

//	修改書籍================================================
	@Override
	public BookStoreResponse revise(BookStoreRequest bookStoreRequest) {
//	//透過Request.getBooktitle()取得表並放到findOutList中
		Optional<BookStore> findOutList = bookStoreDao.findById(bookStoreRequest.getBooktitle());
		// 如果findOutList有資料
		if (findOutList.isPresent()) {
			// 那就get資料放到bookStore中(用一個變數自動新增)
			BookStore bookStore = findOutList.get();
			// 將輸入的Request get並set到bookStore
			bookStore.setAuthor(bookStoreRequest.getAuthor());
			bookStore.setBooktitle(bookStoreRequest.getBooktitle());
			bookStore.setIsbn(bookStoreRequest.getIsbn());
			bookStore.setPrice(bookStoreRequest.getPrice());
			bookStore.setInventory(bookStoreRequest.getInventory());
			bookStore.setSales(bookStoreRequest.getSales());
			bookStore.setType(bookStoreRequest.getType());
			// 將Request中get到的bookStore再存回Dao
			bookStoreDao.save(bookStore);
			return new BookStoreResponse(bookStore, "修改成功");// 建構方法 bt=BookStore
		}
		return new BookStoreResponse("修改失敗");
	}
//	一般查詢書籍==============================================

	@Override
	public List<ReadBookResponse> readBook(String type) {
		// 把以查找種類書訊裝進新清單
		List<BookStore> books = bookStoreDao.findByType(type);
		List<ReadBookResponse> responses = new ArrayList<>();
		// 如果查找到對應分類的書籍
		if (!books.isEmpty()) {
			// 就遍歷該清單
			for (BookStore book : books) {
				// 把書名、ISBN、作者、價格、銷售量get到並加入新new的response並print出
				ReadBookResponse response = new ReadBookResponse("success", book.getBooktitle(), book.getIsbn(),
						book.getAuthor(), book.getPrice(), book.getInventory());
				responses.add(response);
			}
//如果沒有查找到對應分類的書籍就print查無此類別
		} else {
			responses.add(new ReadBookResponse("查無此類別"));
		}
		return responses;
	}

//  消費者 / 書商查詢書籍======================================

	@Override
	public List<ReadBookResponse> findByBookInfo(String identity, String bookInfo) {
		List<BookStore> searchbooks = bookStoreDao.findByBooktitleOrIsbnOrAuthor(bookInfo, bookInfo, bookInfo);
		List<ReadBookResponse> responses = new ArrayList<>();

		// 判定身分是否為消費者
		if (identity.equals("消費者")) {
//			就遍歷清單，
			for (BookStore searchbook : searchbooks) {
//				如果清單不為空，就print出身分為消費者時只回傳書名、ISBN、作者、價格，不回傳庫存及銷售量
				if (!bookInfo.equals("")) {
					responses.add(new ReadBookResponse("success", searchbook.getBooktitle(), searchbook.getIsbn(),
							searchbook.getAuthor(), searchbook.getPrice()));
//				如果為空就print出輸入有誤
				} else {
					responses.add(new ReadBookResponse("輸入有誤"));
				}
			}
			// 身分為書商時回傳全部書籍資訊
		} else if (identity.equals("書商")) {
			// 遍歷根據請求得到的清單
			for (BookStore searchbook : searchbooks) {
				// 如果書訊關鍵字有不為空
				if (!bookInfo.equals("")) {
					// 就回傳書商的對應資訊
					responses.add(new ReadBookResponse("success", searchbook.getBooktitle(), searchbook.getIsbn(),
							searchbook.getAuthor(), searchbook.getPrice(), searchbook.getInventory(),
							searchbook.getSales()));
					// 如果書訊關鍵字為空就顯示輸入有誤
				} else {
					responses.add(new ReadBookResponse("輸入有誤"));
				}
			}

		} else {
			responses.add(new ReadBookResponse("輸入有誤"));
		}

		// 如果搜尋結果為空則回傳查無此書
		if (searchbooks.isEmpty()) {
			responses.add(new ReadBookResponse("查無此書"));
		}

		return responses;
	}

//  更新庫存================================================

	@Override
	public BookStoreResponse updateInventory(String booktitle, int inventory) {
		List<BookStore> bookList = bookStoreDao.findByBooktitle(booktitle);
//如果根據請求找到的書名清單為空
		if (!bookList.isEmpty()) {
			BookStore book = bookList.get(0);
			int originalInventory = book.getInventory();
			int newInventory = originalInventory + inventory;

			book.setInventory(newInventory);
			bookStoreDao.save(book);

			return new BookStoreResponse("成功更新庫存");
		} else {
			return new BookStoreResponse("查無此書籍");
		}
	}

//  更新價格================================================

	@Override
	public BookStoreResponse updatePrice(String booktitle, int price) {
		List<BookStore> bookList = bookStoreDao.findByBooktitle(booktitle);
		// 如果根據請求找到的書名清單為空
		if (!bookList.isEmpty()) {
			BookStore book = bookList.get(0);

			book.setPrice(price);
			bookStoreDao.save(book);

			return new BookStoreResponse("成功修改價格");
		} else {
			return new BookStoreResponse("查無此書籍");
		}
	}

//	購買書籍================================================

	@Override
	public List<ReadBookResponse> buyBooks(BookStoreRequest2 bookStoreRequest2) {
		List<BookStore> list = new ArrayList<>();
		for (int i = 0; i < bookStoreRequest2.getBooks().size(); i++) {
			String booktitle = bookStoreRequest2.getBooks().get(i).getBooktitle();
			// 根據書名請求找到資料庫的書訊放進bookList內
			List<BookStore> bookList = bookStoreDao.findByBooktitle(booktitle);
			// 如果bookList不為空
			if (bookList != null && !bookList.isEmpty()) {
				// 將所有符合標題的書籍加入清單中
				list.addAll(bookList);
			}
		}
		//定義一個finalList為最終購買清單
		List<BookStore> finalList = new ArrayList<>();
		//定義一個count計算買書數量
		int count = 0;
		//定義一個total，計算買書總額
		int total = 0;
		//定義一個
		List<ReadBookResponse> responses = new ArrayList<>();
//如
		if (list.isEmpty()) {
			responses.add(new ReadBookResponse("購物車是空的~"));
		}

		// 遍歷所有符合標題的書籍
		for (BookStore book : list) {

			int inventory = book.getInventory();
			int sales = book.getSales();
			int quantity = 0;
			for (BookStoreRequest req : bookStoreRequest2.getBooks()) {
				if (req.getBooktitle().equals(book.getBooktitle())) {
					quantity = req.getQuantity();
					break;
				}
			}

			if (inventory >= quantity && quantity > 0) { // 如果庫存夠購買，且購買量大於0
				book.setInventory(inventory - quantity); // 更新庫存數量
				book.setSales(sales + quantity);
				total += (book.getPrice() * quantity); // 更新總額
				bookStoreDao.save(book); // 更新資料庫

				finalList.add(book); // 將書籍加入最終購買清單
				count++; // 更新購買次數
			} else if (quantity > 0) { // 購買量大於0，但庫存不足
				responses.add(new ReadBookResponse(book.getBooktitle() + "庫存不足!"));
			}
		}
//如果最終購買清單不為空
		if (!finalList.isEmpty()) {
			String message = "已購買書籍清單：";
			for (BookStore book : finalList) {
				// 把書訊放進message
				message += "書名：" + book.getBooktitle() + "，ISBN：" + book.getIsbn() + "，作者：" + book.getAuthor() + "，價格："
						+ book.getPrice() + "，";
			}
			// 就回傳定義為message的書訊
			responses.add(new ReadBookResponse(message));
			// 回傳總額
			responses.add(new ReadBookResponse("總額：" + total));
			// 回傳購買次數
			responses.add(new ReadBookResponse("購買次數：" + count));
			System.out.println(finalList);
		}

		return responses;
	}

//		找到前五名書籍================================================
	@Override
	// 找到根據銷售量前五名並由大到小排序
	public List<BookStore> findTop5ByOrderBySalesDesc() {
		return bookStoreDao.findTop5ByOrderBySalesDesc();
	}

}
