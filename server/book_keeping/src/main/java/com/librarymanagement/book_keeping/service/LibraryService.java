package com.librarymanagement.book_keeping.service;

import com.librarymanagement.book_keeping.dao.BookDao;
import com.librarymanagement.book_keeping.dto.BooksDto;

public interface LibraryService {

	public BooksDto getBookList();
	public String addBook(BookDao bookDetails);
	public String updateBook(BookDao bookDetails);
	public String deleteBook(String bookId);
}
