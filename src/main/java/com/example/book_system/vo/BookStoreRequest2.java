package com.example.book_system.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookStoreRequest2 {
	@JsonProperty("books")
	private List<BookStoreRequest> books;

	private String booktitle;// 書名
	private int quantity;// 數量

	public List<BookStoreRequest> getBooks() {
		return books;
	}

	public void setBooks(List<BookStoreRequest> books) {
		this.books = books;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
