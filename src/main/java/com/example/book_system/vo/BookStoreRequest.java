package com.example.book_system.vo;

import java.util.List;

import com.example.book_system.entity.BookStore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookStoreRequest {
	@JsonProperty("bookStore")
	private BookStore bookStore;

	@JsonProperty("book_store_list")
	private List<BookStore> bookStorelist;
	private String booktitle;// 書名
	private String isbn;
	private String author;// 作者
	private int price;// 價格
	private int inventory;// 庫存
	private int sales;// 銷售額
	private String type;// 種類
	private String identity;// 身分(消費者、書商)
	private String bookInfo;// 書訊
	private int quantity;// 數量

// getters & setters
	public BookStore getBookStore() {
		return bookStore;
	}

	public void setBookStore(BookStore bookStore) {
		this.bookStore = bookStore;
	}

	public List<BookStore> getBookStorelist() {
		return bookStorelist;
	}

	public void setBookStorelist(List<BookStore> bookStorelist) {
		this.bookStorelist = bookStorelist;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(String bookInfo) {
		this.bookInfo = bookInfo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
