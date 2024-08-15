package com.library_management.book.service.serviceImp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.library_management.applicationData.entities.ApplicationPropertiesConstants;
import com.library_management.applicationData.entities.BaseEntity;
import com.library_management.applicationData.repository.BaseEntityRepository;
import com.library_management.book.entity.Book;
import com.library_management.book.models.request.BookRequestModel;
import com.library_management.book.models.response.BookResModel;
import com.library_management.book.repository.BookRepository;
import com.library_management.book.service.BookService;

import jakarta.transaction.Transactional;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BaseEntityRepository baseEntityRepository;
	
	@Override
	@Transactional
	public BookResModel createBook(BookRequestModel bookRequestModel) throws JsonProcessingException {
		BookResModel result =new BookResModel();
		Book book = new Book();
		BaseEntity baseEntity = new BaseEntity();
		
		book.setAuthor(bookRequestModel.getAuthor());
		book.setCategory(bookRequestModel.getCategory());
		book.setIsbn(bookRequestModel.getIsbn());
		book.setLanguage(bookRequestModel.getLanguage());
		book.setNumberOfPages(bookRequestModel.getNumberOfPages());
		book.setPublicatinYear(bookRequestModel.getPublicationYear());
		book.setReaderAge(bookRequestModel.getReaderAge());
		book.setStatusDescription(bookRequestModel.getStatusDescription());
		book.setTitle(bookRequestModel.getTitle());
		book.setBorrowed(false);
		baseEntity.setCreaterId(ApplicationPropertiesConstants.Library_Management_user);
		baseEntity.setCreationDate(LocalDateTime.now());
		book.setBaseEntity(baseEntity);
		
		baseEntityRepository.save(baseEntity);
		bookRepository.save(book);
		
		result.setSuccess(true);
		result.setMessage("Book added succesfully");
		return result;
	}

	@Override
	@Transactional
	public List<BookRequestModel> retrievebooks() throws JsonProcessingException {
		List<BookRequestModel> result =new ArrayList<>();
		List<Book> bookList = bookRepository.findAll();
		for(int i=0 ;i<bookList.size();i++) {
			Book book =(Book)bookList.get(i);
			BookRequestModel bookRequestModel = new BookRequestModel();
			bookRequestModel.setAuthor(book.getAuthor());
			bookRequestModel.setCategory(book.getCategory());
			bookRequestModel.setIsbn(book.getIsbn());
			bookRequestModel.setLanguage(book.getLanguage());
			bookRequestModel.setNumberOfPages(book.getNumberOfPages());
			bookRequestModel.setPublicationYear(book.getPublicatinYear());
			bookRequestModel.setReaderAge(book.getReaderAge());
			bookRequestModel.setStatusDescription(book.getStatusDescription());
			bookRequestModel.setTitle(book.getTitle());
			result.add(bookRequestModel);
		}
		return result;
	}

	@Override
	@Transactional
	public BookRequestModel getBookById(int id) {
	    Optional<Book> bookOptional = bookRepository.findById(id);
	    if (bookOptional.isPresent()) {
	        Book book = bookOptional.get();
	        BookRequestModel bookRequestModel = new BookRequestModel();
	        bookRequestModel.setAuthor(book.getAuthor());
	        bookRequestModel.setCategory(book.getCategory());
	        bookRequestModel.setIsbn(book.getIsbn());
	        bookRequestModel.setLanguage(book.getLanguage());
	        bookRequestModel.setNumberOfPages(book.getNumberOfPages());
	        bookRequestModel.setPublicationYear(book.getPublicatinYear());
	        bookRequestModel.setReaderAge(book.getReaderAge());
	        bookRequestModel.setStatusDescription(book.getStatusDescription());
	        bookRequestModel.setTitle(book.getTitle());
	        return bookRequestModel;
	    } else {
	        return null;
	    }
	}

	    @Override
	    @Transactional
	    public BookRequestModel updateBook(int id, BookRequestModel bookRequestModel) {
	        Optional<Book> bookOptional = bookRepository.findById(id);
	    
	    	
	        if (bookOptional.isPresent()) {
	            Book book = bookOptional.get();
	            book.setTitle(bookRequestModel.getTitle());
	            book.setAuthor(bookRequestModel.getAuthor());
	            book.setPublicatinYear(bookRequestModel.getPublicationYear());
	            book.setIsbn(bookRequestModel.getIsbn());
	            book.setNumberOfPages(bookRequestModel.getNumberOfPages());
	            book.setStatusDescription(bookRequestModel.getStatusDescription());
	            book.setCategory(bookRequestModel.getCategory());
	            book.setReaderAge(bookRequestModel.getReaderAge());
	            book.setLanguage(bookRequestModel.getLanguage());
	            book.getBaseEntity().setChangeDate(LocalDateTime.now());
	            book.getBaseEntity().setChangerId(ApplicationPropertiesConstants.Library_Management_Update_User);
	            bookRepository.save(book);
	            
	            return bookRequestModel; // Returning the updated book data
	        } else {
	            return null; // Book not found
	        }
	    }

	    
	    @Override
	    @Transactional
	    public BookResModel deleteBook(int id) {
	        BookResModel result = new BookResModel();
	        Optional<Book> bookOpt = bookRepository.findById(id);

	        if (bookOpt.isPresent()) {
	            Book book = bookOpt.get();

	            if(book.isBorrowed()) {
	            	 result.setSuccess(false);
	 	             result.setMessage("Book is borrowed by  patron");
	            }else {
	                // Delete associated baseEntity
	                BaseEntity baseEntity = book.getBaseEntity();
	                if (baseEntity != null) {
	                    baseEntityRepository.delete(baseEntity);
	                 }

	                // Delete the book
	                bookRepository.delete(book);

	                result.setSuccess(true);
	                result.setMessage("Book and associated records deleted successfully");
	                 }
	        }else {
                result.setSuccess(false);
                result.setMessage("Book does not exist in our Library");
             }
	        return result;
	    }


}
