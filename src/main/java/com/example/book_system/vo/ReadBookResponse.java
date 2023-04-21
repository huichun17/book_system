package com.example.book_system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReadBookResponse {
	private String message;// 訊息
	private String booktitle;// 書名
	private String isbn;
	private String author;// 作者
	private Integer price;// 價格
	private Integer inventory;// 庫存
	private Integer sales;// 銷售額

	// 除了銷售額
	public ReadBookResponse(String message, String booktitle, String isbn, String author, int price, int inventory) {
		super();
		this.message = message;
		this.booktitle = booktitle;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
		this.inventory = inventory;
	}

	public ReadBookResponse(String message) {
		super();
		this.message = message;
	}

//消費者建構資料
	public ReadBookResponse(String message, String booktitle, String isbn, String author, int price) {
		super();
		this.message = message;
		this.booktitle = booktitle;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
	}

	// 書商建構資料
	public ReadBookResponse(String message, String booktitle, String isbn, String author, int price, int inventory,
			int sales) {
		super();
		this.message = message;
		this.booktitle = booktitle;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
		this.inventory = inventory;
		this.sales = sales;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

}
