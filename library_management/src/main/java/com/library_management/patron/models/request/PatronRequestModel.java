package com.library_management.patron.models.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class PatronRequestModel {
	  @NotBlank(message = "First name is required.")
	    private String firstName;

	    private String secondName;

	    private String thirdName;

	    @NotNull(message = "Nationality ID is required.")
	    private Long nationalityId;

	    @NotBlank(message = "Gender is required.")
	    private String gender;

	    @NotBlank(message = "Email is required.")
	    @Email(message = "Email should be valid.")
	    private String email;

	    @NotBlank(message = "Username is required.")
	    private String userName;

	    @NotBlank(message = "Password is required.")
	    @Size(min = 6, message = "Password should be at least 6 characters.")
	    private String password;

	    @NotBlank(message = "Phone number is required.")
	    private String phone;

	    @NotBlank(message = "Country is required.")
	    private String country;

	    @NotBlank(message = "City is required.")
	    private String city;

	    @NotBlank(message = "Street is required.")
	    private String street;

	    @NotNull(message = "Position ID is required.")
	    private Integer positionId;

}
