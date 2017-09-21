package com.gamble.drivers_license.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gamble.drivers_license.models.License;
import com.gamble.drivers_license.models.Person;
import com.gamble.drivers_license.services.PersonService;
import com.gamble.drivers_license.services.LicenseService;

@Controller
public class DriversLicenseCtrl {
	
	private final PersonService personService;
	private final LicenseService licenseService;

    public DriversLicenseCtrl(PersonService personService, LicenseService licenseService){
        this.personService = personService;
        this.licenseService = licenseService;
    }
	
    @RequestMapping("/")
    public String home() {
        return "/WEB-INF/views/index.jsp";
    }

    @RequestMapping("/persons/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "/WEB-INF/views/person.jsp";
    }
    
    @PostMapping("/persons/new")
    public String createPerson(@Valid @ModelAttribute("person") Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "/WEB-INF/views/person.jsp";
        }else{
            personService.addPerson(person);
            return "redirect:/";
        }
    }
    
    @RequestMapping("/licenses/new")
    public String newLicense(@ModelAttribute("license") License license,
    								Model model) {
    		Date todays_date = new Date();
    		SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
    		String min_date = pattern.format(todays_date);
    		model.addAttribute("min_date", min_date);
        List<Person> people_list = personService.allPeople();
        model.addAttribute("people_list", people_list);
        System.out.println("before form submission: " + license.getNumber());
    		return "/WEB-INF/views/license.jsp";
    }
    
    
    @PostMapping("/licenses/new")
    public String createLicense(@Valid @ModelAttribute("license") License license, BindingResult result) {
        if (result.hasErrors()) {
            return "/WEB-INF/views/license.jsp";
        }else{
            Long person_id = license.getPerson().getId();
            License old_license = licenseService.findLicenseByPerson(license.getPerson());
            System.out.println("*****" + old_license);
            if (old_license != null) {
            		Date new_date = license.getExpiration_date();
            		String new_state = license.getState();
            		String new_number = license.getNumber();
            		license = old_license;
            		license.setExpiration_date(new_date);
            		license.setState(new_state);
            		license.setNumber(new_number);
            }
            System.out.println("**" + license.getNumber());
            //this actually updates the old license from the db
            // with new lic.number, exp date, and state;
        			licenseService.addLicense(license);
        		return "redirect:/persons/" + person_id;
        }	
    }

    @RequestMapping("/persons/{id}")
	public String searchPage(@PathVariable("id") Long id,
							Model model) {
    		Person person = personService.findPersonById(id);
    		model.addAttribute("person", person);
    		return "/WEB-INF/views/profile.jsp";
    }

}
