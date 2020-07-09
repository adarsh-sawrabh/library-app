package com.librarymanagement.book_keeping.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookDao {

	@NotNull
	private String bookId;
	
	@NotNull(message="Book Name cannot be null")
	private String bookName;
	
	@NotNull(message="Author Name cannot be null")
	@Size(min=3, message="Author Name must more than 2 characters")
	private String author;
	
	@NotNull(message="Publication year cannot be null")
	private String yearOfPublication;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(String yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}
	
}
