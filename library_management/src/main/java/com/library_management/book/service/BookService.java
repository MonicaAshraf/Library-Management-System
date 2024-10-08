package com.library_management.book.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.library_management.book.models.request.BookRequestModel;
import com.library_management.book.models.response.BookResModel;

import jakarta.validation.Valid;

public interface BookService {
	public BookResModel createBook(
			BookRequestModel BookRequestModel)
			throws JsonProcessingException;
	
	public List<BookRequestModel> retrievebooks()
			throws JsonProcessingException;

	public BookRequestModel getBookById(int id);

	public BookRequestModel updateBook(int id, @Valid BookRequestModel bookRequestModel);

	public BookResModel deleteBook(int id);
}
