package com.gamble.counter.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("clicks")
public class CounterCtrl {
	
	@ModelAttribute("clicks")
	public Integer setSessionAttribute() {
		return 0;
	}
	
	@RequestMapping("/")
	public String home(@ModelAttribute("clicks") Integer clicks, Model model) {
		clicks++;
		model.addAttribute("clicks", clicks);
		System.out.println(clicks + "~~~~");
		
		return "index.jsp";
	}
		
	@RequestMapping("/counter")
	public String counter() {
//		System.out.println(clicks);
		return "counterdisplay.jsp";
	}

    @RequestMapping("/clearSession")
    	  public String setSession(SessionStatus session){
        session.setComplete();
        return "redirect:/";
    }

}
