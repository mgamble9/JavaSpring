package com.gamble.events.controllers;

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

import com.gamble.events.models.Comment;
import com.gamble.events.models.Event;
import com.gamble.events.models.Role;
import com.gamble.events.models.User;
import com.gamble.events.services.UserService;
import com.gamble.events.validator.UserValidator;

@Controller
public class EventsCtrl {

	private UserService userService;
    private UserValidator userValidator;
    private static String[] us_state_list = {
            "AK",
                "AL",
                "AR",
                "AS",
                "AZ",
                "CA",
                "CO",
                "CT",
                "DC",
                "DE",
                "FL",
                "GA",
                "GU",
                "HI",
                "IA",
                "ID",
                "IL",
                "IN",
                "KS",
                "KY",
                "LA",
                "MA",
                "MD",
                "ME",
                "MI",
                "MN",
                "MO",
                "MS",
                "MT",
                "NC",
                "ND",
                "NE",
                "NH",
                "NJ",
                "NM",
                "NV",
                "NY",
                "OH",
                "OK",
                "OR",
                "PA",
                "PR",
                "RI",
                "SC",
                "SD",
                "TN",
                "TX",
                "UT",
                "VA",
                "VI",
                "VT",
                "WA",
                "WI",
                "WV",
                "WY"
};
	
    public EventsCtrl(UserService userService, UserValidator userValidator) {
		super();
		this.userService = userService;
		this.userValidator = userValidator;
	}

    @RequestMapping("/")
    public String login(@RequestParam(value="error", required=false) String error,
    						@RequestParam(value="logout", required=false) String logout,
    						@RequestParam(value="registered", required=false) String registered,	
    						Model model,
    						@ModelAttribute("user") User user) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials. Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successfull!");
        }
        if(registered != null) {
            model.addAttribute("registeredMessage", "Registered Successfully!");
        }
        model.addAttribute("us_state_list", us_state_list);
        return "/WEB-INF/loginPage.jsp";
    }
    
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        userValidator.validate(user, result);

	    if (result.hasErrors()) {
	        model.addAttribute("us_state_list", us_state_list);
	        return "/WEB-INF/loginPage.jsp";
	    }
	    
//	    boolean priviledge_flag = false;
//	    List<User> all_users = userService.allUsers();
//	    //System.out.println("All users: " + all_users.toString());
//	    if (all_users.isEmpty()) {
//	    		userService.saveUserWithAdminRole(user);
//	    		System.out.println("**** no users! making an admin!");
//	    	    return "redirect:/login?registered";
//	    }
//	    outerloop:
//	    for (User x: all_users) {
//	    		for (Role y: x.getRoles()) {
//	    			if (y.getName().equals("ROLE_ADMIN")) {
//	    				priviledge_flag = true;
//	    			    userService.saveWithUserRole(user);
//	    				System.out.println("**** making a user!");
//	    				break outerloop;
//	    			}
//	    		}
//	    }
//	    if (priviledge_flag == false) {
//    			userService.saveUserWithAdminRole(user);
//			System.out.println("**** No admin found! making an admin!");
//	    }
	    userService.saveWithUserRole(user);
	    return "redirect:/?registered";
    }

    @RequestMapping("/events")
    public String eventsPage(Principal principal,
    						Model model,
    						@ModelAttribute("event") Event event) {
        String email = principal.getName();
        User currentUser = userService.findByEmail(email);
        List<Event> event_list_in_state = userService.allEventsInState(currentUser.getState());
        List<Event> event_list_out_of_state = userService.allEventsNotInState(currentUser.getState());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("event_list_in_state", event_list_in_state);
        model.addAttribute("event_list_out_of_state", event_list_out_of_state);
        model.addAttribute("us_state_list", us_state_list);
        return "/WEB-INF/eventPage.jsp";
    }

    @PostMapping("/create_event")
    public String eventCreation(@Valid @ModelAttribute("event") Event event,
    								BindingResult result,
    								Model model,
    								Principal principal) {
	    if (result.hasErrors()) {
	        model.addAttribute("us_state_list", us_state_list);
	        String email = principal.getName();
	        User currentUser = userService.findByEmail(email);
	        model.addAttribute("currentUser", currentUser);
	        List<Event> event_list_in_state = userService.allEventsInState(currentUser.getState());
	        List<Event> event_list_out_of_state = userService.allEventsNotInState(currentUser.getState());
	        model.addAttribute("event_list_in_state", event_list_in_state);
	        model.addAttribute("event_list_out_of_state", event_list_out_of_state);
	        return "/WEB-INF/eventPage.jsp";
	    }
	    userService.saveEvent(event);
	    return "redirect:/events";
    }

    @RequestMapping(value="/events/{id}/edit")
    public String eventEdit(@PathVariable("id") Long id,
    								Model model) {    
    		Event event = userService.findEventById(id);
        if (event != null){
            model.addAttribute("event", event);
            model.addAttribute("us_state_list", us_state_list);
            return "/WEB-INF/editEvent.jsp";
        }else{
            return "redirect:/events";
        }
    }
    
    @PostMapping("/events/edit")
    public String eventUpdate(@Valid @ModelAttribute("event") Event event,
    								BindingResult result,
    								Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("us_state_list", us_state_list);
	        return "/WEB-INF/editEvent.jsp";
	    }
	    userService.saveEvent(event);
	    return "redirect:/events";
    }

    @RequestMapping("/events/join/{id}")
    public String eventJoin(@PathVariable("id") Long id,
							Model model,
							Principal principal) {
    		Event event = userService.findEventById(id);
            if (event != null){
            		String email = principal.getName();
            		User currentUser = userService.findByEmail(email);
            		List<User> attendee_list = event.getAttendees();
            		attendee_list.add(currentUser);
            		event.setAttendees(attendee_list);
            		userService.saveEvent(event);
                return "redirect:/events";
            }else{
                return "redirect:/events";
            }
        }

    @RequestMapping("/events/cancel/{id}")
    public String eventCancelJoin(@PathVariable("id") Long id,
							Model model,
							Principal principal) {
    		Event event = userService.findEventById(id);
            if (event != null){
            		String email = principal.getName();
            		User currentUser = userService.findByEmail(email);
            		List<User> attendee_list = event.getAttendees();
            		attendee_list.remove(currentUser);
            		event.setAttendees(attendee_list);
            		userService.saveEvent(event);
                return "redirect:/events";
            }else{
                return "redirect:/events";
            }
        }
    
    @RequestMapping("/events/{id}")
    public String wallMessage(@PathVariable("id") Long id,
    						Principal principal,
    						Model model,
    						@ModelAttribute("comment") Comment comment) {
		Event event = userService.findEventById(id);
        if (event != null){
	        String email = principal.getName();
	        User currentUser = userService.findByEmail(email);
	        model.addAttribute("currentUser", currentUser);
	        List<User> attendee_list = event.getAttendees();
	        List<Comment> comment_list = event.getComments();
	        int attendee_list_size = attendee_list.size();
	        model.addAttribute("attendee_list", attendee_list);
	        model.addAttribute("comment_list", comment_list);
	        model.addAttribute("attendee_list_size", attendee_list_size);
            model.addAttribute("event", event);
            return "/WEB-INF/wallPage.jsp";
        }else{
            return "redirect:/events";
        }
    }

    @PostMapping("/events/{id}")
    public String wallPageUpdate(@Valid @ModelAttribute("comment") Comment comment,
    								BindingResult result,
    								Model model,
    								@PathVariable("id") Long id) {
	    if (result.hasErrors()) {
	    		Event event = userService.findEventById(id);
	        List<User> attendee_list = event.getAttendees();
	        int attendee_list_size = attendee_list.size();
	        List<Comment> comment_list = event.getComments();
	        model.addAttribute("attendee_list", attendee_list);
	        model.addAttribute("comment_list", comment_list);
	        model.addAttribute("attendee_list_size", attendee_list_size);
            model.addAttribute("event", event);
	        return "/WEB-INF/wallPage.jsp";
	    }
	    userService.saveComment(comment);
	    return "redirect:/events/" + id;
    }


    @RequestMapping(value="/events/delete/{id}")
    public String destroyEvent(@PathVariable("id") Long id) {
        userService.destroyEvent(id);
        return "redirect:/events";
    }

    //following is needed for href link
    @RequestMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }    

}
