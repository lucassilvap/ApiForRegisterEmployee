package com.example.test.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.model.Employee;
import com.example.test.service.EmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Employee employee){
		return ResponseEntity.ok(employeeService.save(employee));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> find(@PathVariable(value = "id") Long id){
		 return ResponseEntity.ok(employeeService.find(id));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Employee employee){
		return ResponseEntity.ok(employeeService.update(id, employee));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
		employeeService.softDelete(id);
		return ResponseEntity.ok().build();	
	}
	
	@GetMapping
	public ResponseEntity<?> page(
	@RequestParam(value = "name",required = false) Optional<String> name,
	@RequestParam(value = "salary",required = false) Optional<Double> salary,
	@RequestParam(value = "employeeFunction", required = false) Optional<String> employeeFunction, 
	@RequestParam(value = "page", required = false)  Optional<Integer> page,
	@RequestParam(value = "size", required = false)  Optional<Integer> size){
		
	     return ResponseEntity.ok
	     (employeeService.page(name, salary, employeeFunction, page, size));
	}
	
	@GetMapping("/employeefunction")
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(employeeService.findALLEmployeeFunction());
	}
	
}
