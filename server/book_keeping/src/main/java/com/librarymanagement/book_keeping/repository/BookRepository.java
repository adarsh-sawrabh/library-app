package com.librarymanagement.book_keeping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagement.book_keeping.entity.Books;

public interface BookRepository extends JpaRepository<Books, Long>{

	public List<Books> findAll();
	public Books findByBookId(String bookId);
}
