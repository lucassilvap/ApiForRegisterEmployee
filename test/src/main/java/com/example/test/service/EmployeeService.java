package com.example.test.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.example.test.exception.EmployeeNameMustBeUniqueException;
import com.example.test.exception.EmployeeNotFoundException;
import com.example.test.model.Employee;
import com.example.test.repository.EmployeRepository;
import com.example.test.specification.EmployeeSpecification;

@Service
public class EmployeeService {

	@Autowired
	private EmployeRepository employeRepository;
	
	@Autowired
	private PageService pageService;
	
	public Employee save (Employee employee) {
	 validateEmployee(employee);
	 return employeRepository.save(employee);	
    }
	
	public Employee find(Long id) {
		Optional<Employee> employee = employeRepository.findById(id);
		if(employee.isPresent())
		if(!employee.get().getIsActive())
		throw new EmployeeNotFoundException();
		return employee.get();
	}
	
	private void validateEmployee(Employee employee) {
		if (employeRepository
		.findByNameIgnoreCaseAndIsActiveTrue(employee.getName())
		.isPresent())
		throw new EmployeeNameMustBeUniqueException();
	}

	public Employee update(Long id, Employee updateEmployee) {
		Employee oldEmployee = find(id);
		if (!(oldEmployee.getName()
		.equalsIgnoreCase(updateEmployee.getName())))
		validateEmployee(updateEmployee);
		updateEmployee.setId(id);
		return employeRepository.save(updateEmployee);
	}
	
	public void softDelete(Long id) {
		Employee employee = find(id);
		employee.setIsActive(false);
		employeRepository.save(employee);
	}
	
	public Page<Employee> page(Optional<String> name, 
	  Optional<Double> salary, Optional<String> employeeFunction,
	  Optional<Integer> page, Optional<Integer> size){
		
	   return employeRepository
	  .findAll(EmployeeSpecification.spec(name, salary, employeeFunction),
       pageService.pageEmployee(size, page));
	}
	
	public List<String> findALLEmployeeFunction() {
		return employeRepository.findAllEmployeeFunction();
	}
	
	public List<Employee> findAll(){
		return employeRepository.findAll();
	}
	
}
