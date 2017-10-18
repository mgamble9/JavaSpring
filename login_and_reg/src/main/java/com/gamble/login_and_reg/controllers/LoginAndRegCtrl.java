package com.gamble.login_and_reg.controllers;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gamble.login_and_reg.models.User;
import com.gamble.login_and_reg.services.UserService;
import com.gamble.login_and_reg.validator.UserValidator;



@Controller
public class LoginAndRegCtrl {

	private UserService userService;
    private UserValidator userValidator;

    
    public LoginAndRegCtrl(UserService userService, UserValidator userValidator) {
    this.userService = userService;
    this.userValidator = userValidator;

    }

    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error,
    						@RequestParam(value="logout", required=false) String logout,
    						@RequestParam(value="registered", required=false) String registered,	
    						Model model,
    						@ModelAttribute("user") User user) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successfull!");
        }
        if(registered != null) {
            model.addAttribute("registeredMessage", "Registered Successfully!");
        }
        return "/WEB-INF/loginPage.jsp";
    }
    
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        userValidator.validate(user, result);

	    if (result.hasErrors()) {
	        return "/WEB-INF/loginPage.jsp";
	    }
	    userService.saveWithUserRole(user);
	    return "redirect:/login?registered";
    }

    
    @RequestMapping("/dashboard")
    public String home(Principal principal, Model model) {
        String email = principal.getName();
        User currentUser = userService.findByEmail(email);
		Date newdate = new Date();
		currentUser.setUpdatedAt(newdate);
		userService.updateUserWithLoginDate(currentUser);
        model.addAttribute("currentUser", currentUser);
        
        return "/WEB-INF/homePage.jsp";
    }
    
    @RequestMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }    
}
