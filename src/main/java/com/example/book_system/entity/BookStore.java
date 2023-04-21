package com.example.book_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bookStore")
public class BookStore {
	@Id
	@Column(name = "booktitle")
	private String booktitle;// 書名
	@Column(name = "isbn")
	private String isbn;
	@Column(name = "author")
	private String author;// 作者
	@Column(name = "price")
	private int price;// 價格
	@Column(name = "inventory")
	private int inventory;// 庫存
	@Column(name = "sales")
	private int sales;// 銷售量
	@Column(name = "type")
	private String type;// 種類

//預設建構方法

	public BookStore() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 全建構方法
	public BookStore(String booktitle, String iSBN, String author, int price, int inventory, int sales, String type) {
		super();
		this.booktitle = booktitle;
		isbn = iSBN;
		this.author = author;
		this.price = price;
		this.inventory = inventory;
		this.sales = sales;
		this.type = type;
	}
	
	
	public BookStore(String type) {
		super();
		this.type = type;
	}
	// getters&setters
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
