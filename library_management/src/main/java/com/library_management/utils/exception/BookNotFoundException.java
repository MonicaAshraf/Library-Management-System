package com.library_management.utils.exception;

import lombok.*;

@Setter
@Getter
public class BookNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7246046191874459976L;
    
	private String message;
	
	public BookNotFoundException(String message) {
		this.message = message;
	}
}
