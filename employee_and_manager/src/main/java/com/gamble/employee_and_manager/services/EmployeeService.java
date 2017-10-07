package com.gamble.employee_and_manager.services;

import org.springframework.stereotype.Service;

import com.gamble.employee_and_manager.models.Employee;
import com.gamble.employee_and_manager.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	
	public Employee findOne(long id) {
		return employeeRepository.findOne(id);
	}
	
}
