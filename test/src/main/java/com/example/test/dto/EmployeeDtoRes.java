package com.example.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class EmployeeDtoRes {
	
	@JsonIgnore
	private Long id;
	
    private String name;
	
	private double salary;
	
	private String employee_function;
}
