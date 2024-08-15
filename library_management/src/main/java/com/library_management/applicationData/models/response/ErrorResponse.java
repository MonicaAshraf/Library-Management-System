package com.library_management.applicationData.models.response;

import lombok.*;

@Setter
@Getter
public class ErrorResponse {

	 private int statusCode;
	 private String message;

	 public ErrorResponse(int statusCode, String message) {
	        this.statusCode = statusCode;
	        this.message = message;
	    }
}
