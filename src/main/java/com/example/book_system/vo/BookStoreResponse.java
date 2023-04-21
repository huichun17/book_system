package com.example.book_system.vo;

import java.util.List;

import com.example.book_system.entity.BookStore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BookStoreResponse {
	@JsonProperty("book_store")
	private BookStore bookStore;// 取用Entity重複屬性
	@JsonProperty("book_store_list")
	private List<BookStore> bookStoreList;
	@JsonProperty("message")
	private String message;
	@JsonProperty("quantity")
	private Integer quantity;//
	@JsonProperty("total")
	private Integer total;// 購買總額
	@JsonProperty("newInventory")
	private Integer newInventory;
	public BookStoreResponse(BookStore bookStore, int quantity, int total, int newInventory) {
	    this.bookStore = bookStore;
	    this.quantity = quantity;
	    this.total = total;
	    this.newInventory = newInventory;
	}
//	public BookStoreResponse(String booktitle, String isbn, String author, int price, int quantity, int total,
//			int newInventory) {
//		this.bookStore = new BookStore(booktitle, isbn, author, price, newInventory, newInventory, author);
//		this.quantity = quantity;
//		this.total = total;
//		this.newInventory = newInventory;
//	}
//
//	public BookStoreResponse(BookStore bookStore, Integer quantity, Integer total, Integer newInventory) {
//		this.bookStore = bookStore;
//		this.quantity = quantity;
//		this.total = total;
//		this.newInventory = newInventory;
//	}

	// 空的建構方法

	// 建構方法
//for新增刪除用的建構方法
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BookStoreResponse(BookStore bookStore, String message) {
		super();
		this.bookStore = bookStore;
		this.message = message;
	}

	// list & 訊息建構方法
	public BookStoreResponse(List<BookStore> bookStoreList, String message) {
		super();
		this.bookStoreList = bookStoreList;
		this.message = message;
	}

	public BookStoreResponse(String message) {
		super();
		this.message = message;
	}

	// 全建構方法

	public BookStoreResponse(BookStore bookStore, List<BookStore> bookStoreList, String message, Integer total) {
		super();
		this.bookStore = bookStore;
		this.bookStoreList = bookStoreList;
		this.message = message;
		this.total = total;
	}

	// getters & setters

	public BookStore getBookStore() {
		return bookStore;
	}

	public void setBookStore(BookStore bookStore) {
		this.bookStore = bookStore;
	}

	public List<BookStore> getBookStoreList() {
		return bookStoreList;
	}

	public void setBookStoreList(List<BookStore> bookStoreList) {
		this.bookStoreList = bookStoreList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getNewInventory() {
		return newInventory;
	}

	public void setNewInventory(Integer newInventory) {
		this.newInventory = newInventory;
	}

}
