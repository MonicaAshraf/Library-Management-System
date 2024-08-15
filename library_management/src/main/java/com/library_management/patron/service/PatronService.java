package com.library_management.patron.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.library_management.patron.models.request.PatronRequestModel;
import com.library_management.patron.models.response.PatronResModel;

import jakarta.validation.Valid;

public interface PatronService {

	public PatronResModel createpatron(@Valid PatronRequestModel patronRequestModel)throws JsonProcessingException;

	public List<PatronRequestModel> getAllPatrons()
			throws JsonProcessingException;

	public PatronRequestModel getPatronById(int id);

	public PatronResModel updatePatron(int id, @Valid PatronRequestModel patronRequestModel);

	public PatronResModel deletePatron(int id);


}
