package com.example.test.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.dto.EmployeeDtoRes;
import com.example.test.dto.EmployeeReqDto;
import com.example.test.model.Employee;

@Service
public class EmployeeMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Employee dtoForEntity(EmployeeReqDto dto) {
		return modelMapper.map(dto, Employee.class);
	}
	
	public EmployeeDtoRes entityForDto(Employee employee) {
		return modelMapper.map(employee, EmployeeDtoRes.class);
	}
	
	public List<EmployeeDtoRes> allForDto(List<Employee> employees){
		return employees.stream()
		.map(employee -> entityForDto(employee))
		.collect(Collectors.toList());
	}
	
}
