package com.gamble.group_languages.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gamble.group_languages.models.Languages;
import com.gamble.group_languages.services.LanguageService;

@Controller
public class GroupLanguages {

	private final LanguageService languageService;

    public GroupLanguages(LanguageService languageService){
        this.languageService = languageService;
    }

    @RequestMapping("/languages")
    public String languages(Model model,
    							@ModelAttribute("language") Languages language) {
        List<Languages> languages_list = languageService.allLanguages();
        model.addAttribute("languages", languages_list);
        return "/WEB-INF/views/index.jsp";
    }

    @PostMapping("/languages")
    public String createLanguageEntry(@Valid @ModelAttribute("language") Languages language,
    										BindingResult result) {
        if (result.hasErrors()) {
            return "/WEB-INF/views/index.jsp";
        }else{
            // Add the language
        		languageService.addLanguage(language);
            return "redirect:/languages";
        }
    }

    @RequestMapping("/languages/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        Languages language = languageService.findLanguageByIndex(id);
        if (language != null){
            model.addAttribute("language", language);
            return "/WEB-INF/views/displayLanguage.jsp";
        }else{
            return "redirect:/languages";
        }
    }

    @RequestMapping(value="/languages/delete/{id}")
    public String destroyLanguage(@PathVariable("id") int id) {
        languageService.destroyLanguage(id);
        return "redirect:/languages";
    }
    
    @RequestMapping("/languages/edit/{id}")
    public String editLanguage(@PathVariable("id") int id, Model model) {
        Languages language = languageService.findLanguageByIndex(id);
        if (language != null){
            model.addAttribute("language", language);
            return "/WEB-INF/views/editLanguage.jsp";
        }else{
            return "redirect:/languages";
        }
    }

    @PostMapping("/languages/edit/{id}")
    public String updateLanguage(@PathVariable("id") int id,
    								@Valid @ModelAttribute("language") Languages language,
    								BindingResult result) {
        if (result.hasErrors()) {
            return "/WEB-INF/views/editLanguage.jsp";
        }else{
            languageService.updateLanguage(id, language);
            return "redirect:/languages";
        }
    }

}
