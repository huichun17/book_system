package com.example.book_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book_system.entity.BookStore;
import com.example.book_system.vo.BookStoreRequest;
import com.example.book_system.vo.BookStoreRequest2;
import com.example.book_system.vo.ReadBookResponse;

@Repository
public interface BookStoreDao extends JpaRepository<BookStore, String> {
//書籍分類搜尋，輸入要的type到MySQL查找並打包對應資訊
	List<BookStore> findByType(String type);

//消費者及書商根據關鍵字到MySQL搜尋書名、ISBN、作者欄位查找並打包對應資訊  1.Isbn 2.3個?
	List<BookStore> findByBooktitleOrIsbnOrAuthor(String keyword1, String keyword2, String keyword3);

	// 根據書名到MySQL查找對應書籍以便修改庫存量及金額
	List<BookStore> findByBooktitle(String booktitle);

//根據請求購買書籍
	List<BookStore> findByBooktitle(BookStoreRequest2 bookStoreRequest2);

	// 找銷售前五
	List<BookStore> findTop5ByOrderBySalesDesc();

}
