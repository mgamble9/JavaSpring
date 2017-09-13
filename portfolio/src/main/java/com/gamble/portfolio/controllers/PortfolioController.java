package com.gamble.portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortfolioController {
	@RequestMapping("/")
	public String index() {
		return "forward:/index.html";
	}
	
	@RequestMapping("/metoo")
	public String me() {
		return "forward:/me.html";
	}

	@RequestMapping("/porty")
	public String portfolio() {
		return "forward:/portfolio.html";
	}

}
