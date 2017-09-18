package com.gamble.languages_reloaded.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gamble.languages_reloaded.models.Languages;
import com.gamble.languages_reloaded.services.LanguageService;

@Controller
public class LanguagesReloaded {

	private final LanguageService languageService;

    public LanguagesReloaded(LanguageService languageService){
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
    public String editBook(@PathVariable("id") Long id, Model model) {
        Languages language = languageService.findLanguageById(id);
        if (language != null){
            model.addAttribute("language", language);
            return "/WEB-INF/views/displayLanguage.jsp";
        }else{
            return "redirect:/languages";
        }
    }

    @RequestMapping(value="/languages/delete/{id}")
    public String destroyLanguage(@PathVariable("id") Long id) {
        languageService.destroyLanguage(id);
        return "redirect:/languages";
    }
    
    @RequestMapping("/languages/edit/{id}")
    public String editLanguage(@PathVariable("id") Long id, Model model) {
        // Languages language = languageService.findLanguageByIndex(id);
        Languages language = languageService.findLanguageById(id);

        if (language != null){
            model.addAttribute("language", language);
            return "/WEB-INF/views/editLanguage.jsp";
        }else{
            return "redirect:/languages";
        }
    }

    @PostMapping("/languages/edit/{id}")
    public String updateLanguage(
    								@Valid @ModelAttribute("language") Languages language,
    								BindingResult result) {
        if (result.hasErrors()) {
            return "/WEB-INF/views/editLanguage.jsp";
        }else{
            languageService.updateLanguage(language);
            return "redirect:/languages";
        }
    }

}
