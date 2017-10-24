package com.gamble.admin_dashboard.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gamble.admin_dashboard.models.User;
import com.gamble.admin_dashboard.models.Role;
import com.gamble.admin_dashboard.services.UserService;
import com.gamble.admin_dashboard.validator.UserValidator;

@Controller
public class AdminDashboardCtrl {

	private UserService userService;
    private UserValidator userValidator;

    
    public AdminDashboardCtrl(UserService userService, UserValidator userValidator) {
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
	    
	    boolean priviledge_flag = false;
	    List<User> all_users = userService.allUsers();
	    //System.out.println("All users: " + all_users.toString());
	    if (all_users.isEmpty()) {
	    		userService.saveUserWithAdminRole(user);
	    		System.out.println("**** no users! making an admin!");
	    	    return "redirect:/login?registered";
	    }
	    outerloop:
	    for (User x: all_users) {
	    		for (Role y: x.getRoles()) {
	    			if (y.getName().equals("ROLE_ADMIN")) {
	    				priviledge_flag = true;
	    			    userService.saveWithUserRole(user);
	    				System.out.println("**** making a user!");
	    				break outerloop;
	    			}
	    		}
	    }
	    if (priviledge_flag == false) {
    			userService.saveUserWithAdminRole(user);
			System.out.println("**** No admin found! making an admin!");
	    }
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
    
    @RequestMapping("/admin")
    public String adminPage(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("currentUser", userService.findByEmail(email));
	    List<User> user_list = userService.allUsers();
        model.addAttribute("user_list", user_list);
        return "/WEB-INF/adminPage.jsp";
    }
    
    @RequestMapping(value="/admin/delete/{id}")
    public String destroyUser(@PathVariable("id") Long id) {
        userService.destroyUser(id);
        return "redirect:/admin";
    }

    @RequestMapping(value="/admin/make_admin/{id}")
    public String makeUserAdmin(@PathVariable("id") Long id) {    
    		User user = userService.findById(id);
        user.setRoles(userService.findRoleByName("ROLE_ADMIN"));
        userService.updateUserWithAdminRole(user);
        return "redirect:/admin";
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
