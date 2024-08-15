package com.library_management.book.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResModel {

	@NotNull
	private Boolean success;

	private String message;

	
}
