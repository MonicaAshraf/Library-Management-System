package com.library_management.utils.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.library_management.applicationData.models.response.ErrorResponse;

@ControllerAdvice
public class AppExceptionsHandler {
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
	
	
	  @ExceptionHandler(BookNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException ex) {
	        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	    }

	  
	  
	    @ExceptionHandler(PatronNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handlePatronNotFoundException(PatronNotFoundException ex) {
	        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(BorrowingRecordNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleBorrowingRecordNotFoundException(BorrowingRecordNotFoundException ex) {
	        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
	        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred");
	        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    
//	    @ExceptionHandler(Exception.class)
//	    public ResponseEntity<Map<String, String>> handleGenericExceptions(Exception ex) {
//	        Map<String, String> response = new HashMap<>();
//	        response.put("statusCode", "500");
//	        response.put("message", "An unexpected error occurred");
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
}
