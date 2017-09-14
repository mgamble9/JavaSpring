package com.gamble.hello_human.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class HelloHumanCtrl {

	@RequestMapping("/")
	public String index(@RequestParam(value = "f", required = false, defaultValue = "Human") String firstName,
			@RequestParam(value = "l", required = false, defaultValue = "Name") String lastName, Model model) {
		model.addAttribute("name", firstName + " " + lastName);
		return "index.jsp";
	}
	
	
}
