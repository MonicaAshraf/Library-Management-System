package com.library_management.borrowingRecord.service.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library_management.applicationData.entities.ApplicationPropertiesConstants;
import com.library_management.applicationData.entities.BaseEntity;
import com.library_management.applicationData.repository.BaseEntityRepository;
import com.library_management.book.entity.Book;
import com.library_management.book.repository.BookRepository;
import com.library_management.borrowingRecord.entity.BorrowingRecord;
import com.library_management.borrowingRecord.repository.BorrowingRecordRepository;
import com.library_management.borrowingRecord.service.BorrowService;
import com.library_management.patron.entity.Patron;
import com.library_management.patron.models.response.PatronResModel;
import com.library_management.patron.repository.PatronRepository;
import com.library_management.utils.exception.BookNotFoundException;
import com.library_management.utils.exception.BorrowingRecordNotFoundException;
import com.library_management.utils.exception.PatronNotFoundException;

import jakarta.transaction.Transactional;
@Service
public class BorrowServiceImpl implements BorrowService{
	
	@Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private PatronRepository patronRepository;
    
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;
    
    @Autowired
	BaseEntityRepository baseEntityRepository;

    @Transactional
    @Override
    public PatronResModel borrowBook(int bookId, int patronId) {
        PatronResModel response = new PatronResModel();
        
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        Optional<Patron> patronOpt = patronRepository.findById(patronId);
        
        if (!bookOpt.isPresent()) {
            response.setSuccess(false);
            response.setMessage("Book not found in our Library");
            return response;
        }
        
        if (!patronOpt.isPresent()) {
            response.setSuccess(false);
            response.setMessage("Patron not registar in our Library , so please registar first");
            return response;
        }
        
        Book book = bookOpt.get();
        Patron patron = patronOpt.get();
        
        // Check if the book is already borrowed
        if (book.isBorrowed()) {
            response.setSuccess(false);
            response.setMessage("Book is already borrowed");
            return response;
        }
        
        // Create and save borrowing record
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());
        
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setCreaterId(ApplicationPropertiesConstants.Library_Management_user);
		baseEntity.setCreationDate(LocalDateTime.now());	
		baseEntityRepository.save(baseEntity);
		
		borrowingRecord.setBaseEntity(baseEntity);
        borrowingRecordRepository.save(borrowingRecord);
        
        // Update book status
        book.setBorrowed(true);
        bookRepository.save(book);
        
        response.setSuccess(true);
        response.setMessage("Book borrowed successfully");
        return response;
    }

    
	@Override
	@Transactional
    public PatronResModel returnBook(int bookId, int patronId) {
        PatronResModel response = new PatronResModel();
        
//        Optional<Book> bookOpt = bookRepository.findById(bookId);
//        Optional<Patron> patronOpt = patronRepository.findById(patronId);
        
//        if (!bookOpt.isPresent()) {
//            response.setSuccess(false);
//            response.setMessage("Book not found");
//            return response;
//        }
        
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId + " not found"));
        
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException("Patron with ID " + patronId + " not found"));
        
//        if (!patronOpt.isPresent()) {
//            response.setSuccess(false);
//            response.setMessage("Patron not found");
//            return response;
//        }
        
        //Book book = bookOpt.get();
//        Patron patron = patronOpt.get();
        
        // Check if the book was borrowed by the patron
        
        BorrowingRecord record = borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron)
                .orElseThrow(() -> new BorrowingRecordNotFoundException("No borrowing record found for this patron and book"));
        
        
//        Optional<BorrowingRecord> recordOpt = borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron);
//        if (!recordOpt.isPresent()) {
//            response.setSuccess(false);
//            response.setMessage("No borrowing record found for this patron and book");
//            return response;
//        }
        
//        BorrowingRecord record = recordOpt.get();
        record.setReturnDate(LocalDate.now());
        record.getBaseEntity().setChangeDate(LocalDateTime.now());
        record.getBaseEntity().setChangerId(ApplicationPropertiesConstants.Library_Management_Update_User);
        borrowingRecordRepository.save(record);
        
        // Update the book status
        book.setBorrowed(false);
        bookRepository.save(book);
        
        response.setSuccess(true);
        response.setMessage("Book returned successfully");
        return response;
        
        
     
    }

}
