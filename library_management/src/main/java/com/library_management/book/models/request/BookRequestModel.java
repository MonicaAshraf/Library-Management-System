package com.library_management.book.models.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
public class BookRequestModel {
	
	    @NotBlank(message = "Title is required.")
	    private String title;

	    @NotBlank(message = "Author is required.")
	    private String author;

	    @NotNull(message = "Publication year is required.")
	    private Integer publicationYear;

	    @Size(max = 13, message = "ISBN should not exceed 13 characters.")
	    private String isbn;

	    @NotNull(message = "Number of pages is required.")
	    private Integer numberOfPages;

	    private String statusDescription;

	    @NotBlank(message = "Category is required.")
	    private String category;

	    @NotNull(message = "Reader age is required.")
	    private Integer readerAge;

	    @NotBlank(message = "Language is required.")
	    private String language;
}
