package com.example.test.specification;

import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import com.example.test.model.Employee;

public abstract class EmployeeSpecification {
		
	private static Specification<Employee> byName(String name){
		return (root, cq , cb) -> cb.equal(root.get("name"), name);
	}
	
	private static Specification<Employee> bySalary(double salary){
		return (root, cq, cb) -> cb.equal(root.get("salary"), salary);
	}
	
	private static Specification<Employee> byEmployeeFunction(String employeeFunction){
		return (root, cq, cb) -> cb.equal(root.get("employee_function"), employeeFunction);
	}
	
	public static Specification<Employee> spec(Optional<String> name, 
	Optional<Double> salary, Optional<String> employeeFunction){
	    Specification<Employee> spec = null;
	    
	    return Specification.where(spec)
	   .and(name.isPresent() ? byName(name.get()) : spec)
	   .and(salary.isPresent() ? bySalary(salary.get()) : spec)
	   .and(employeeFunction.isPresent() ? 
		byEmployeeFunction(employeeFunction.get()) : spec);
	}

}
