package com.example.test.controller;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.test.service.CsvService;

@RestController
@RequestMapping("csv")
public class CsvController {

	@Autowired
	private CsvService csvService;
	
	@GetMapping(value = "/exportCSV", produces = "text/csv")
	public ResponseEntity<Resource> exportCSV(
	       @RequestParam(value = "name", required = false) 
	       Optional<String> name,
	
			@RequestParam(value = "salary",required = false) Optional<Double> salary,
			@RequestParam(value = "employeeFunction", required = false) Optional<String> employeeFunction, 
			@RequestParam(value = "page", required = false)  Optional<Integer> page,
			@RequestParam(value = "size", required = false)  Optional<Integer> size) {
			
	    String csvFileName = "employee.csv";

	    // setting HTTP headers
	    HttpHeaders headers = new HttpHeaders();
	    headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFileName);
	    // defining the custom Content-Type
	    headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

	    return new ResponseEntity<>(
	            csvService.
	            csvEmployee(name, salary, employeeFunction, page, size),
	            headers,
	            HttpStatus.OK
	    );
	}
	
}	
