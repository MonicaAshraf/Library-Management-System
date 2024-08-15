package com.library_management.utils.exception;

import lombok.*;

@Setter
@Getter
public class PatronNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6948663132060278498L;

	private String message;
	
	public PatronNotFoundException( String message) {
		super();
		this.message = message;
	}

}
