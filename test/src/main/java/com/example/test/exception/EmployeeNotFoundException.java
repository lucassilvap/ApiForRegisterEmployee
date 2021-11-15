package com.example.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmployeeNotFoundException extends ResponseStatusException {

	public EmployeeNotFoundException() {
		super(HttpStatus.BAD_REQUEST, "employee not found !!");
		// TODO Auto-generated constructor stub
	}

}
