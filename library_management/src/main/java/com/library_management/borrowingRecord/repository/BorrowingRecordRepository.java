package com.library_management.borrowingRecord.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library_management.book.entity.Book;
import com.library_management.borrowingRecord.entity.BorrowingRecord;
import com.library_management.patron.entity.Patron;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer>{
	 Optional<BorrowingRecord> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);

}
