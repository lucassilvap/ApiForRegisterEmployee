package com.example.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmployeeNameMustBeUniqueException extends ResponseStatusException {

	public EmployeeNameMustBeUniqueException() {
		super(HttpStatus.BAD_REQUEST, "name must be unique!!!");
		// TODO Auto-generated constructor stub
	}

}
