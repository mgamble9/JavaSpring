package com.gamble.dojo_and_ninjas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gamble.dojo_and_ninjas.models.Dojo;
import com.gamble.dojo_and_ninjas.models.Ninja;
import com.gamble.dojo_and_ninjas.services.DojoService;
import com.gamble.dojo_and_ninjas.services.NinjaService;


@Controller
public class DojoAndNinjasCtrl {
	
	private final DojoService dojoService;
	private final NinjaService ninjaService;

    public DojoAndNinjasCtrl(DojoService dojoService, NinjaService ninjaService){
        this.dojoService = dojoService;
        this.ninjaService = ninjaService;
    }
	
    @RequestMapping("/")
    public String home() {
        return "/WEB-INF/views/index.jsp";
    }

    @RequestMapping("/dojos/new")
    public String newDojo(@ModelAttribute("dojo") Dojo dojo) {
        return "/WEB-INF/views/dojo.jsp";
    }
    
    @PostMapping("/dojos/new")
    public String createDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
        if (result.hasErrors()) {
            return "/WEB-INF/views/dojo.jsp";
        }else{
        		if (dojoService.findDojoByName(dojo.getName()) != null) {
        			System.out.println("**Dojo Name exists already");
        			return "/WEB-INF/views/dojo.jsp";
        		}
            dojoService.addDojo(dojo);
            return "redirect:/";
        }
    }

    @RequestMapping("/ninjas/new")
    public String newNinja(@ModelAttribute("ninja") Ninja ninja,
    										Model model) {
    		List<Dojo> dojo_list = dojoService.allDojos();
    		model.addAttribute("dojo_list", dojo_list);
    		System.out.println("***Hey!");
    		return "/WEB-INF/views/ninja.jsp";
    }
    
    @PostMapping("/ninjas/new")
    public String createNinja(@Valid @ModelAttribute("ninja") Ninja ninja,
    							BindingResult result,
    							Model model) {
        if (result.hasErrors()) {
        		System.out.println("ugh****");
        		List<Dojo> dojo_list = dojoService.allDojos();
        		model.addAttribute("dojo_list", dojo_list);
            return "/WEB-INF/views/ninja.jsp";
        } else {
        		ninjaService.addNinja(ninja);
        		return "redirect:/";
        }
    }
    
    @RequestMapping("/dojos/{id}")
    public String viewDojo(@PathVariable("id") int id, Model model) {
    		Dojo dojo = dojoService.findDojoById((long) id);
    		if (dojo != null) {
    			model.addAttribute("dojo", dojo);
    			return "/WEB-INF/views/dojo_members.jsp";
    		} else {
    			return "redirect:/";
    		}
    }
    
}
