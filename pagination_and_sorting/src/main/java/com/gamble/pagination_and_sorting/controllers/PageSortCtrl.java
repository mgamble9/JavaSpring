package com.gamble.pagination_and_sorting.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gamble.pagination_and_sorting.models.Ninja;
import com.gamble.pagination_and_sorting.services.NinjaService;

@Controller
public class PageSortCtrl {

	private final NinjaService ninjaService;

    public PageSortCtrl(NinjaService ninjaService){
        this.ninjaService = ninjaService;
    }

    @RequestMapping("/")
    public String home() {
        return "/WEB-INF/views/index.jsp";
    }

    @RequestMapping("/pages/{pageNumber}")
    public String getNinjasPerPage(Model model, @PathVariable("pageNumber") int pageNumber) {
        // we have to subtract 1 because the Pages iterable is 0 indexed. This is for our links to be able to show from 1...pageMax, instead of 0...pageMax - 1.
        Page<Ninja> ninjas = ninjaService.ninjasPerPage(pageNumber - 1);

        // total number of pages that we have
        int totalPages = ninjas.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("ninjas", ninjas);
        return "/WEB-INF/views/ninjas.jsp";
    }

}
