package com.library_management.utils.exception;

import lombok.*;

@Setter
@Getter
public class BorrowingRecordNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6994859018150889694L;

	
	private String message;
	
	public BorrowingRecordNotFoundException( String message) {
		super();
		this.message = message;
	}

}
