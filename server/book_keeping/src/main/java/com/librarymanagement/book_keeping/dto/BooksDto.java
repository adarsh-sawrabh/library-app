package com.librarymanagement.book_keeping.dto;

import java.io.Serializable;
import java.util.List;

import com.librarymanagement.book_keeping.entity.Books;

public class BooksDto  implements Serializable{

	private static final long serialVersionUID = -9058774830891034187L;
	
	private List<Books> bookList;
	
	public List<Books> getBookList() {
		return bookList;
	}
	public void setBookList(List<Books> bookList2) {
		this.bookList = bookList2;
	}
	
}
