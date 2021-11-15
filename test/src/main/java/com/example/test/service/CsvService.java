package com.example.test.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.test.model.Employee;

@Service
public class CsvService {
	
   @Autowired
   private EmployeeService  employeeService;
	
   public InputStreamResource csvEmployee(Optional<String> name, Optional<Double> salary,
   Optional<String> employeeFunction, Optional<Integer> page,
   Optional<Integer> size) {
	   
	   Page pageEmployee = 
	   employeeService.page(name, salary, employeeFunction, page, size);
	   
	   String[] csvHeader = {
	            "id", "name", "salary", "employee_function"
	   };
	   
	   List<Employee> employees = pageEmployee.toList();
	   ByteArrayInputStream byteArrayOutputStream;

	    try (
	            ByteArrayOutputStream out = new ByteArrayOutputStream();
	            // defining the CSV printer
	            CSVPrinter csvPrinter = new CSVPrinter(
	                    new PrintWriter(out),
	                    // withHeader is optional
	                    CSVFormat.DEFAULT.withHeader(csvHeader)
	            );
	    ) {
	        // populating the CSV content
	        for (Employee employeeUnique : employees)
	        csvPrinter.printRecord(employeeUnique.getId() 
	        +" " +employeeUnique.getName()+" " + 
	        employeeUnique.getSalary() + " " + 
	        employeeUnique.getEmployee_function());

	        // writing the underlying stream
	        csvPrinter.flush();

	        byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	        throw new RuntimeException(e.getMessage());
	    }

	    InputStreamResource fileInputStream = 
	    new InputStreamResource(byteArrayOutputStream);
      
	    return fileInputStream;
	    
   }

}
