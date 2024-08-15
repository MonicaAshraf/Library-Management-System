package com.library_management.borrowingRecord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library_management.borrowingRecord.service.BorrowService;
import com.library_management.patron.models.response.PatronResModel;

@RestController
@RequestMapping()
@Validated
public class BorrowingRecordController {

	@Autowired
	BorrowService borrowService;

	
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<PatronResModel> borrowBook(@PathVariable int bookId, @PathVariable int patronId) {
        PatronResModel response = borrowService.borrowBook(bookId, patronId);
        if (response.getSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    
    
//    @PutMapping("/return/{bookId}/patron/{patronId}")
//    public ResponseEntity<PatronResModel> returnBook(@PathVariable int bookId, @PathVariable int patronId) {
//        PatronResModel response = borrowService.returnBook(bookId, patronId);
//        if (response.getSuccess()) {
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
//    }
    
    
    
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<PatronResModel> returnBook(@PathVariable int bookId, @PathVariable int patronId) {
        PatronResModel response = borrowService.returnBook(bookId, patronId);
        return ResponseEntity.ok(response);
    }
}
