package com.service.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serivce.book.dto.BookDTO;
import com.service.book.IService.BookService;
import com.service.book.misc.Utility;

@RestController
@RequestMapping("/books")
public class BooksController {
	
	@Autowired
	private BookService bookService;

	@GetMapping
	public ResponseEntity<List<BookDTO>> getAllBooks(){
		return new ResponseEntity<List<BookDTO>>(bookService.getAllBooks(),HttpStatus.OK);
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable("bookId") Long bookId){
		return new ResponseEntity<BookDTO>(bookService.getBookById(bookId),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody BookDTO bookDTO) {
		if (!Utility.validateRequest(bookDTO)) {
			return new ResponseEntity<String>("Please ensure all inputs are correct !!", HttpStatus.BAD_REQUEST);
		}
		if (bookService.addBook(bookDTO)) {
			return new ResponseEntity<String>("Book Id :"+ bookDTO.getBookId() + " book info added sucessfully !!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Error occurered, Please try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable("bookId") Long bookId){
		if(bookService.deleteBook(bookId)) {
			return new ResponseEntity<String>("Book record deleted successfully !! ",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Issue while deleting record Please try aftersome time !! ",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/{bookId}")
	public ResponseEntity<String> updateBook(@RequestBody BookDTO bookDTO){
		if(bookDTO == null || bookDTO.getBookId() == null) {
			return new ResponseEntity<String>("Book Id is mandatory !!", HttpStatus.BAD_REQUEST);
		}
		if(bookService.updateBook(bookDTO)) {
			return new ResponseEntity<String>("Book Id :"+ bookDTO.getBookId() + " book info updated sucessfully !!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Error occurered, Please try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
