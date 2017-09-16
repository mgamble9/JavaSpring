package com.gamble.ninjagold.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes({"gold","results"})
public class NInjaGold {

	@ModelAttribute("results")
	public ArrayList<String> setSessionResultsAttribute() {
		ArrayList<String> results_list = new ArrayList<String>();
		results_list.add("<p style=\"color:green;\">Welcome To Ninja Gold!</p>");
		return results_list;
	}

	@ModelAttribute("gold")
	public Integer setSessionGoldAttribute() {
		return 0;
	}

	@RequestMapping("/")
	public String index() {
		return "WEB-INF/views/index.jsp";
	}

	@RequestMapping(path="/process_money", method=RequestMethod.POST)
	public String login(@RequestParam(value="building", required=true) String building,
						@ModelAttribute("gold") Integer gold_tot,
						@ModelAttribute("results") ArrayList<String> results_list,
						Model model) {
		System.out.println(building);
		Random ran = new Random();
		Date newdate = new Date();
		Integer gold_change = 0;
		SimpleDateFormat pattern = new SimpleDateFormat("MMMM d yyyy h:mm a");
		String date_displayed = pattern.format(newdate);
		String string_out = "";

		if (building.equalsIgnoreCase("farm")) {
			gold_change = ran.nextInt(11) +10;
			string_out = "<p style=\"color:green;\">You entered a farm and stole " +
					gold_change + " gold. " + date_displayed + "</p>";
		}
		else if (building.equalsIgnoreCase("cave")) {
			gold_change = ran.nextInt(6) +5;
			string_out = "<p style=\"color:green;\">You entered a cave and found " +
					gold_change + " gold. " + date_displayed + "</p>";
		}	
		else if (building.equalsIgnoreCase("house")) {
			gold_change = ran.nextInt(4) +2;
			string_out = "<p style=\"color:green;\">You entered a house and stole " +
					gold_change + " gold. " + date_displayed + "</p>";
		}	
		else if (building.equalsIgnoreCase("casino")) {
			gold_change = ran.nextInt(101) -50;
			if (gold_change < 0) {
				string_out = "<p style=\"color:red;\">You entered a casino and lost " +
						gold_change + " gold. " + date_displayed + "</p>";
			} 
			else {
				string_out = "<p style=\"color:green;\">You entered a casino and won " +
						gold_change + " gold. " + date_displayed + "</p>";
			}
		}
		
		gold_tot += gold_change;
		System.out.println("gold change is: " + gold_change);
		model.addAttribute("gold", gold_tot);
		results_list.add(string_out);
		return "redirect:/";		
	}

    @RequestMapping("/clearSession")
	  	public String setSession(SessionStatus session){
    		session.setComplete();
    	return "redirect:/";
}

}
