package com.example.test.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.test.model.Employee;

@Repository
public interface EmployeRepository extends
    CrudRepository<Employee, Long>, JpaSpecificationExecutor<Employee>{

	public Optional<Employee> findByNameIgnoreCaseAndIsActiveTrue(String name);
	
    @Query( value = "SELECT DISTINCT e.employee_function FROM Employee e",
    nativeQuery = true)
    List<String> findAllEmployeeFunction();
    
    public List<Employee> findAll();
}
