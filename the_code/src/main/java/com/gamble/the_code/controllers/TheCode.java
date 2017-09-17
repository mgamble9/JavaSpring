package com.gamble.the_code.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TheCode {

	@RequestMapping("/")
	public String index() {
		return "WEB-INF/views/index.jsp";
	}
	
	@RequestMapping(path="/process_code_guess", method=RequestMethod.POST)
	public String login(@RequestParam(value="code_guess", required=true) String code_guess,
						RedirectAttributes redirectAttributes) {
		if (code_guess.equalsIgnoreCase("bushido")) {
			return "WEB-INF/views/code.jsp";
		}
        redirectAttributes.addFlashAttribute("error", "You must train harder!");
		return "redirect:/";		
	}
}
