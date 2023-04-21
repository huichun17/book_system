package com.example.book_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_system.entity.BookStore;
import com.example.book_system.repository.BookStoreDao;
import com.example.book_system.service.ifs.BookStoreService;
import com.example.book_system.vo.BookStoreRequest;
import com.example.book_system.vo.BookStoreRequest2;
import com.example.book_system.vo.BookStoreResponse;

import com.example.book_system.vo.ReadBookResponse;

@RestController
public class BookStoreController {
	@Autowired
	private BookStoreService bookStoreService;

//新增書籍
	@PostMapping("/add_Book")
	public BookStoreResponse addBook(@RequestBody BookStoreRequest bookStoreRequest) {
		return bookStoreService.addBook(bookStoreRequest);
	}

//修改
	@PostMapping("/revise")
	public BookStoreResponse revise(@RequestBody BookStoreRequest bookStoreRequest) {
		return bookStoreService.revise(bookStoreRequest);
	}

//查詢
	@PostMapping("/read_Book")
	public List<ReadBookResponse> readBook(@RequestBody BookStoreRequest bookStoreRequest) {
		String type = bookStoreRequest.getType();
		return bookStoreService.readBook(type);
	}

//消費者跟書商查詢的書訊
	@PostMapping("/find_By_Book_Info")
	public List<ReadBookResponse> findByBookInfo(@RequestBody BookStoreRequest bookStoreRequest) {
		String identity = bookStoreRequest.getIdentity();
		String bookInfo = bookStoreRequest.getBookInfo();
		return bookStoreService.findByBookInfo(identity, bookInfo);
	}

//更新庫存
	@PostMapping("/update_Inventory")
	public BookStoreResponse updateInventory(@RequestBody BookStoreRequest bookStoreRequest) {
		String booktitle = bookStoreRequest.getBooktitle();
		int inventory = bookStoreRequest.getInventory();
		return bookStoreService.updateInventory(booktitle, inventory);
	}

//更新價格
	@PostMapping("/update_Price")
	public BookStoreResponse updatePrice(@RequestBody BookStoreRequest bookStoreRequest) {
		String booktitle = bookStoreRequest.getBooktitle();
		int price = bookStoreRequest.getPrice();
		return bookStoreService.updatePrice(booktitle, price);
	}

//購買書
	@PostMapping("/buy_books")

	public List<ReadBookResponse> buyBooks(@RequestBody BookStoreRequest2 bookStoreRequest2) {
		return bookStoreService.buyBooks(bookStoreRequest2);
	}

//找出銷售前五
	@PostMapping("/find_Top_5_By_Order_By_Sales_Desc")
	public List<BookStore> findTop5ByOrderBySalesDesc() {
		return bookStoreService.findTop5ByOrderBySalesDesc();
	}

}
