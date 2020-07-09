package com.librarymanagement.book_keeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Books implements Serializable{

	private static final long serialVersionUID = -9104196418575648351L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String bookId;
	
	@Column(nullable = false, length = 150)
	private String bookName;
	
	@Column(nullable = false)
	private String author;
	
	@Column(nullable = false)
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
