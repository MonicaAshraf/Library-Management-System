package com.library_management.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.library_management.book.models.request.BookRequestModel;
import com.library_management.book.models.response.BookResModel;
import com.library_management.book.service.BookService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books") // http://localhost:8080/LibraryManagement/api/books
@Validated
public class BookController {

	@Autowired
	BookService bookService;
	
	@PostMapping()
	public ResponseEntity<BookResModel> CreateBook(
			@Valid @RequestBody BookRequestModel bookRequestModel,
			 HttpServletRequest request) throws JsonProcessingException {	
		return new ResponseEntity<>(bookService.createBook(bookRequestModel),
				HttpStatus.OK);
		
	}
	
	
	@GetMapping
	public ResponseEntity<List<BookRequestModel>> Retrievebooks() throws JsonProcessingException {	
		return new ResponseEntity<>(bookService.retrievebooks(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookRequestModel> getBookById(@PathVariable("id") int id) {
	    BookRequestModel book = bookService.getBookById(id);
	    if (book != null) {
	        return new ResponseEntity<>(book, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	
	    @PutMapping("/{id}")
	    public ResponseEntity<BookRequestModel> updateBook(
	            @PathVariable("id") int id,
	            @Valid @RequestBody BookRequestModel bookRequestModel) {
	        
	        BookRequestModel updatedBook = bookService.updateBook(id, bookRequestModel);
	        
	        if (updatedBook != null) {
	            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	

	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<BookResModel> deleteBook(@PathVariable("id") int id) {
	    	return new ResponseEntity<>(bookService.deleteBook(id),
					HttpStatus.OK);

	    }
}
