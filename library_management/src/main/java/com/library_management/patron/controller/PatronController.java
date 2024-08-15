package com.library_management.patron.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.library_management.book.models.request.BookRequestModel;
import com.library_management.patron.models.request.PatronRequestModel;
import com.library_management.patron.models.response.PatronResModel;
import com.library_management.patron.service.PatronService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/patrons") // http://localhost:8080/LibraryManagement/api/patrons
@Validated
public class PatronController {

	@Autowired
	PatronService patronService;
	
	@PostMapping()
	public ResponseEntity<PatronResModel> CreateBook(
			@Valid @RequestBody PatronRequestModel patronRequestModel,
			 HttpServletRequest request) throws JsonProcessingException {	
		return new ResponseEntity<>(patronService.createpatron(patronRequestModel),
				HttpStatus.OK);
	}
	
	
	@GetMapping()
	public ResponseEntity<List<PatronRequestModel>> getAllPatrons() throws JsonProcessingException {
	    return new ResponseEntity<>(patronService.getAllPatrons(), HttpStatus.OK);
	}

	
	
	@GetMapping("/{id}")
	public ResponseEntity<PatronRequestModel> getPatronById(@PathVariable int id) throws JsonProcessingException {
	    PatronRequestModel patron = patronService.getPatronById(id);
	    if (patron != null) {
	        return new ResponseEntity<>(patron, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<PatronResModel> updatePatron(
	        @PathVariable int id,
	        @Valid @RequestBody PatronRequestModel patronRequestModel) throws JsonProcessingException {
	    
	    PatronResModel updatedPatron = patronService.updatePatron(id, patronRequestModel);
	    
	    if (updatedPatron != null) {
	        return new ResponseEntity<>(updatedPatron, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PatronResModel> deletePatron(@PathVariable int id) throws JsonProcessingException {
	    PatronResModel result = patronService.deletePatron(id);
	    
	    if (result.getSuccess()) {
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
	    }
	}


	
	
	

}
