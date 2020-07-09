package com.librarymanagement.book_keeping.service;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.book_keeping.dao.BookDao;
import com.librarymanagement.book_keeping.dto.BooksDto;
import com.librarymanagement.book_keeping.entity.Books;
import com.librarymanagement.book_keeping.repository.BookRepository;

@Service
public class LibraryServiceImpl implements LibraryService{
	
	private BookRepository repository;
	
	
	@Autowired
	public LibraryServiceImpl(BookRepository repository) {
		this.repository = repository;
	}

	@Override
	public BooksDto getBookList() {
		
		List<Books> bookList = repository.findAll();
		BooksDto bookDto = new BooksDto();
		bookDto.setBookList(bookList);
		return bookDto;
	}

	@Override
	public String addBook(BookDao bookDetails) {
		bookDetails.setBookId(UUID.randomUUID().toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Books book = modelMapper.map(bookDetails, Books.class);
		repository.saveAndFlush(book);
		return bookDetails.getBookName();
	}

	@Override
	public String updateBook(BookDao bookDetails) {
		Books fetchedbook = repository.findByBookId(bookDetails.getBookId());
		fetchedbook.setBookName(bookDetails.getBookName());
		fetchedbook.setAuthor(bookDetails.getAuthor());
		fetchedbook.setYearOfPublication(bookDetails.getYearOfPublication());
		repository.saveAndFlush(fetchedbook);
		return bookDetails.getBookName();
	}

	@Override
	public String deleteBook(String bookId) {
		Books fetchedbook = repository.findByBookId(bookId);
		repository.delete(fetchedbook);
		return fetchedbook.getBookName();
	}

}
