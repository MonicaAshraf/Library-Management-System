package com.library_management.borrowingRecord.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BorrowingRecordResModel {
	@NotNull
	private Boolean success;

	private String message;
}
