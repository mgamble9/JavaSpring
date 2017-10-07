package com.gamble.employee_and_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gamble.employee_and_manager.models.Employee;
import com.gamble.employee_and_manager.services.EmployeeService;

@Controller
public class EmpManCtrl {

	private final EmployeeService employeeService;
	
	public EmpManCtrl(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}


	@RequestMapping("/")
	public String home() {
		Employee manager1 = employeeService.findOne(Long.valueOf(4));
		System.out.println("Manager's name: " + manager1.getFullname());		
		System.out.println("***");
		System.out.println("Manager's boss: " + manager1.getManager().getFullname());		
		for (Employee x: manager1.getManagers_crew()) {
			System.out.println("Employee's name: " + x.getFullname());
		}
		System.out.println("");
		return "/WEB-INF/views/index.jsp";
	}
	
}
