package com.gamble.bnb.controllers;

import java.security.Principal;
import java.util.ArrayList;
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

import com.gamble.bnb.models.Listing;
import com.gamble.bnb.models.Review;
import com.gamble.bnb.models.Role;
import com.gamble.bnb.models.User;
import com.gamble.bnb.services.UserService;
import com.gamble.bnb.validator.UserValidator;

@Controller
public class BnbCtrl {

	private UserService userService;
    private UserValidator userValidator;
    private String[] pool_size_list = {"Small", "Medium", "Large"};

    
    public BnbCtrl(UserService userService, UserValidator userValidator) {
		super();
		this.userService = userService;
		this.userValidator = userValidator;
	}

    @RequestMapping("/")
    public String homePage(@ModelAttribute("search") String location) {
        return "/WEB-INF/homePage.jsp";
    }

    @RequestMapping("/guest/signin")
    public String loginPage(@RequestParam(value="error", required=false) String error,
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
    			List<Role> user_role_list = userService.findAllRoles();
    			List<String> user_role_option_list = new ArrayList<String>();
    			for (Role role: user_role_list) {
    				user_role_option_list.add(role.getName().substring(5));
    			}
    			model.addAttribute("user_role_option_list", user_role_option_list);
    			return "/WEB-INF/loginPage.jsp";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user,
    		BindingResult result,
    		Model model
    		) {
        userValidator.validate(user, result);

	    if (result.hasErrors()) {
			List<Role> user_role_list = userService.findAllRoles();
			List<String> user_role_option_list = new ArrayList<String>();
			for (Role role: user_role_list) {
				user_role_option_list.add(role.getName().substring(5));
			}
			model.addAttribute("user_role_option_list", user_role_option_list);
	        return "/WEB-INF/loginPage.jsp";
	    }
	    
	    if (user.getRole().contentEquals("GUEST")) {
	    		userService.saveUserWithGuestRole(user);
	    		System.out.println("**** making a guest!");
	    }
	    else if (user.getRole().contentEquals("HOST")) {
    			userService.saveUserWithHostRole(user);
    			System.out.println("**** making a host!");
	    }
	    return "redirect:/guest/signin?registered";
    }

    @RequestMapping("/decision")
    public String decision(Principal principal) {
    		String email = principal.getName();
        User currentUser = userService.findByEmail(email);
        System.out.println("user: " + currentUser.getFull_name());
        if (currentUser.getRoles().contains(userService.findRoleByName("ROLE_GUEST"))) {
        		return "redirect:/";
        }
        else if (currentUser.getRoles().contains(userService.findRoleByName("ROLE_HOST"))) {
    			return "redirect:/host/dashboard";
        }
        return "redirect:/";
    }
      
    @RequestMapping("/search")
    public String searchPage(@RequestParam(value="location", required=true) String search_str,
    							Model model,
    							@ModelAttribute("search") String location
    							) {
		List<Listing> listing_search_list = userService.searchListings(search_str);
		model.addAttribute("listing_search_list",listing_search_list);
		return "/WEB-INF/searchPage.jsp";

    }


    @RequestMapping("/host/dashboard")
    public String dashboardPage(Principal principal,
    								Model model,
    								@ModelAttribute("listing") Listing listing) {
    		String email = principal.getName();
    		User current_user = userService.findByEmail(email);
    		model.addAttribute("current_user", current_user);
        List<Listing> hosts_listings = current_user.getListings();
        model.addAttribute("hosts_listings", hosts_listings);
        model.addAttribute("pool_size_list", pool_size_list);
    		return "/WEB-INF/dashboardPage.jsp";
    }
    
    @PostMapping("/host/dashboard")
    public String dashboardNewListing(Principal principal,
    									Model model,
    									@Valid @ModelAttribute("listing") Listing listing,
    						    			BindingResult result	
    									) {
	    if (result.hasErrors()) {
	    		String email = principal.getName();
	    		User current_user = userService.findByEmail(email);
	    		model.addAttribute("current_user", current_user);
	        List<Listing> hosts_listings = current_user.getListings();
	        model.addAttribute("hosts_listings", hosts_listings);
	        model.addAttribute("pool_size_list", pool_size_list);
	        return "/WEB-INF/dashboardPage.jsp";
	    }
	    userService.saveListing(listing);
		return "redirect:/host/dashboard";
    }
    
    @RequestMapping("/pools/{id}")
    	public String listingDisplay (Model model,
    								@PathVariable("id") Long id) {
    		Listing listing = userService.findListing(id);
    		model.addAttribute("listing",listing);
    		model.addAttribute("review_list", listing.getReviews());
	    return "/WEB-INF/listingPage.jsp";
    }
    		
    @RequestMapping("/pools/{id_listing}/review")
	public String listingReview (Principal principal,
								Model model,
								@PathVariable("id_listing") Long id_listing,
								@ModelAttribute("review") Review review
								) {
    		String email = principal.getName();
		User current_user = userService.findByEmail(email);
		model.addAttribute("current_user", current_user);
        if (current_user.getRoles().contains(userService.findRoleByName("ROLE_HOST"))) {
			return "redirect:/host/dashboard";
        }
		Listing listing = userService.findListing(id_listing);
		model.addAttribute("listing",listing);
		model.addAttribute("review_list", listing.getReviews());
		return "/WEB-INF/reviewPage.jsp";
    }
    
    @PostMapping("/pools/{id_listing}/review")
    public String listingReviewSubmit(Principal principal,
    									@PathVariable("id_listing") Long id_listing,
    									Model model,
    									@Valid @ModelAttribute("review") Review review,
    						    			BindingResult result	
    									) {
	    if (result.hasErrors()) {
	    	// ****
	    	// If you use "id" here instead of "id_listing", somehow the form
	    // uses the "id" variable (being the id for the listing)
	    	// for the id of the review (which subsequently doesn't have a create_at)
	    	// and then subsequent reviews will write over that row entry which
	    	// will have null for create_at property... it only occurs when you
	    	// first start to enter reviews for that listing.
	    	// ****
			Listing listing = userService.findListing(id_listing);
			model.addAttribute("listing",listing);
			String email = principal.getName();
			User current_user = userService.findByEmail(email);
			model.addAttribute("current_user", current_user);
			return "/WEB-INF/reviewPage.jsp";
	    }
	    userService.saveReview(review);
		return "redirect:/pools/" + id_listing;
    }

    @RequestMapping("/host/pools/{id}")
	public String listingEdit (Principal principal,
			Model model,
			@PathVariable("id") Long id
			) {
			String email = principal.getName();
			User current_user = userService.findByEmail(email);
			model.addAttribute("current_user", current_user);
			Listing listing = userService.findListing(id);
	        if (listing != null){
				model.addAttribute("listing",listing);
				model.addAttribute("review_list", listing.getReviews());
		        model.addAttribute("pool_size_list", pool_size_list);
				return "/WEB-INF/listingEditPage.jsp";
	        }else{
	            return "redirect:/host/dashboard";
	        }

    }

    @PostMapping("/host/pools/{id}")
    public String listingEditSubmit(Principal principal,
			@PathVariable("id") Long id,
			Model model,
			@Valid @ModelAttribute("listing") Listing listing,
    			BindingResult result	
			) {
		if (result.hasErrors()) {
			String email = principal.getName();
			User current_user = userService.findByEmail(email);
			model.addAttribute("current_user", current_user);
			Listing listing_old = userService.findListing(id);
			model.addAttribute("listing_old",listing_old);
			model.addAttribute("review_list", listing.getReviews());
	        model.addAttribute("pool_size_list", pool_size_list);
			return "/WEB-INF/listingEditPage.jsp";
		}
		userService.updateListing(listing);
		return "redirect:/pools/" + id;
	}

    //following is needed for href link
    @RequestMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/guest/signin?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }    

    
}
