package com.gamble.employee_and_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.employee_and_manager.models.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	
}
