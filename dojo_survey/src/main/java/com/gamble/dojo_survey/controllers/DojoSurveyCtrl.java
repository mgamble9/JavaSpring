package com.gamble.dojo_survey.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes({"name","location","language","comment"})
public class DojoSurveyCtrl {
	
	@RequestMapping("/")
//	public String index(@ModelAttribute("name") String name,
//						@ModelAttribute("location") String location,
//						@ModelAttribute("language") String language,
//						@ModelAttribute("comment") String comment) {
	public String index() {
		return "index.jsp";
	}

	@RequestMapping(path="/login", method=RequestMethod.POST)
	public String login(@RequestParam(value="name", required=true) String name,
						@RequestParam(value="location", required=true) String location,
						@RequestParam(value="language", required=true) String language,
						@RequestParam(value="comment", defaultValue="") String comment,
//						@ModelAttribute("name") String nm,
//						@ModelAttribute("location") String l,
//						@ModelAttribute("language") String la,
//						@ModelAttribute("comment") String c,
						Model model) {
		System.out.println("****");
		model.addAttribute("name", name);
		model.addAttribute("location", location);
		model.addAttribute("language", language);
		model.addAttribute("comment", comment);
		return "redirect:/result";		
	}
	
	@RequestMapping("/result")
	public String dashboard() {
		return "dashboard.jsp";
	}
	
						
}
