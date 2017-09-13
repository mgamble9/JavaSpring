package com.gamble.displaydate.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DisplayDateController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/date")
	public String datepage(Model model) {
		Date newdate = new Date();
		SimpleDateFormat pattern = new SimpleDateFormat("EEEE, 'the' d 'of' MMMM, yyyy");
		String date_displayed = pattern.format(newdate);
		model.addAttribute("date_displayed", date_displayed);
		return "date_template";
	}
	
	@RequestMapping("/time")
	public String timepage(Model model) {
		Date newdate = new Date();
		SimpleDateFormat pattern = new SimpleDateFormat("h:mm a");
		String time_displayed = pattern.format(newdate);
		model.addAttribute("time_displayed", time_displayed);
		return "time_template";
	}

}
