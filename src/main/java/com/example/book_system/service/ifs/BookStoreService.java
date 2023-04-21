package com.example.book_system.service.ifs;

import java.util.List;

import com.example.book_system.entity.BookStore;
import com.example.book_system.vo.BookStoreRequest;
import com.example.book_system.vo.BookStoreRequest2;
import com.example.book_system.vo.BookStoreResponse;
import com.example.book_system.vo.ReadBookResponse;

public interface BookStoreService {

	// 新增書籍
	public BookStoreResponse addBook(BookStoreRequest bookStoreRequest);

	// 修改書籍
	public BookStoreResponse revise(BookStoreRequest bookStoreRequest);

	// 搜尋書籍
	List<ReadBookResponse> readBook(String type);

	// 消費者與書商取得書訊
	List<ReadBookResponse> findByBookInfo(String identity, String bookInfo);

	// 更改庫存(postman輸入書名及要+的庫存數)
	public BookStoreResponse updateInventory(String booktitle, int inventory);

	// 覆蓋價格(postman輸入書名及要覆蓋的價格)
	public BookStoreResponse updatePrice(String booktitle, int price);

	// 購買書籍(輸入書名跟數量
	List<ReadBookResponse> buyBooks(BookStoreRequest2 bookStoreRequest2);

    //找出銷售前五名
	public List<BookStore> findTop5ByOrderBySalesDesc();

}
