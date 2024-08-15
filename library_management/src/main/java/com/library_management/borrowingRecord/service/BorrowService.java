package com.library_management.borrowingRecord.service;

import com.library_management.patron.models.response.PatronResModel;

public interface BorrowService {

	public PatronResModel borrowBook(int bookId, int patronId);

	public PatronResModel returnBook(int bookId, int patronId);

}
