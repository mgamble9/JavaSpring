package com.gamble.counter.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("clicks")
public class CounterCtrl {
	
	@ModelAttribute("clicks")
	public Integer getSessionAttribute() {
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
//	public String counter(@ModelAttribute("clicks") Integer clicks) {
	public String counter() {
//		System.out.println(clicks);
		return "counterdisplay.jsp";
	}

    @RequestMapping("/clearSession")
//  public String setSession(@ModelAttribute("clicks") Integer clicks, Model model){
//    public String setSession(Model model, HttpSession session){
    	  public String setSession(HttpSession session){
//        model.addAttribute("clicks", 0);
        session.invalidate();
        return "redirect:/";
    }

}
