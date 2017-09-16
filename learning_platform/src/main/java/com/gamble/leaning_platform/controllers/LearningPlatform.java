package com.gamble.leaning_platform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LearningPlatform {

	@RequestMapping("/")
	public String index() {
		return "/WEB-INF/views/index.jsp";
	}

    @RequestMapping("/m/{part1}/{part2}/{part3}")
    public String showProduct(@PathVariable("part1") String chapter,
    								@PathVariable("part2") String discard,
    								@PathVariable("part3") String lesson){
    		System.out.println("showProduct");
    		if (chapter.equals("38")) {
    			if (lesson.equals("0345")) {
    				return "/WEB-INF/views/assignment.jsp"; // the slash before WEB-INF is critical
    			}
    			else if (lesson.equals("0733") || lesson.equals("0342")) {
    				return "/WEB-INF/views/lesson.jsp";
    			} 			
    		}
    		else if (chapter.equals("26")) {
    			if (lesson.equals("2342")) {
    				return "/WEB-INF/views/assignment.jsp";
    			}
    			else if (lesson.equals("0348") || lesson.equals("0342")) {
    				return "/WEB-INF/views/lesson.jsp";
    			} 			
    		}
		return "/WEB-INF/views/index.jsp";
    }
}
