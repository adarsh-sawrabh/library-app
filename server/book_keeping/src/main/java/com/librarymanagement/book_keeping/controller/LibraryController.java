package com.librarymanagement.book_keeping.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.book_keeping.dao.BookDao;
import com.librarymanagement.book_keeping.dto.BooksDto;
import com.librarymanagement.book_keeping.models.BookAddModel;
import com.librarymanagement.book_keeping.models.BookUpdateModel;
import com.librarymanagement.book_keeping.service.LibraryService;

@RestController
@RequestMapping(path="/library")
public class LibraryController {

	@Autowired
	private Environment env;
	
	@Autowired
	private LibraryService libraryService;
	
	@GetMapping(path="/status")
	public String status() {
		return "Library-Management-System is Up on : " + env.getProperty("local.server.port");
	}
	
	@GetMapping(path="/books")
	public ResponseEntity<BooksDto> getBookList() {
		return ResponseEntity.status(HttpStatus.OK).body(libraryService.getBookList());
	}
	
	@PostMapping(
			path="/add_book")
	public ResponseEntity<String> addBook(@Valid@RequestBody BookAddModel  bookDetails){
		try {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			BookDao bookDao = modelMapper.map(bookDetails, BookDao.class);
			String res = libraryService.addBook(bookDao);
			return ResponseEntity.status( HttpStatus.CREATED).body(res +" added successfully");
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occured while  adding  book");
		}
	}
	
	@PutMapping(path="/update_book")
	public ResponseEntity<String> updateBook(@Valid@RequestBody BookUpdateModel  bookDetails){
		try {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			BookDao bookDao = modelMapper.map(bookDetails, BookDao.class);
			String res = libraryService.updateBook(bookDao);
			return ResponseEntity.status( HttpStatus.CREATED).body(res +" updated successfully");
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occured while  updating  book");
		}	
	}
	
	@PostMapping(path="/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable String bookId){
		String res = libraryService.deleteBook(bookId);
		return ResponseEntity.status( HttpStatus.CREATED).body(res +" deleted successfully");
	}
}
