package com.gamble.courseSignup.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.gamble.courseSignup.models.Course;
import com.gamble.courseSignup.models.User;
import com.gamble.courseSignup.services.UserService;
import com.gamble.courseSignup.validator.UserValidator;

@Controller
@SessionAttributes({"semester_session_var"})
public class courseCtrl {

	@ModelAttribute("semester_session_var")
	public Integer setSessionSemesterSessionVarAttribute() {
		return 0;
	}
	
	private static final int DECEMBER_CUTOFF_DAY = 15;
	private static final int DECEMBER = 12;
	private UserService userService;
    private UserValidator userValidator;

    public courseCtrl(UserService userService, UserValidator userValidator) {
		super();
		this.userService = userService;
		this.userValidator = userValidator;
	}

    @RequestMapping("/")
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
		return "/WEB-INF/loginPage.jsp";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user,
    							BindingResult result,
    							Model model ) {
        userValidator.validate(user, result);
	    if (result.hasErrors()) {
	        return "/WEB-INF/loginPage.jsp";
	    }
	    	userService.saveUser(user);
	    	System.out.println("**** making a user!");
	    return "redirect:/?registered";
    }

    @RequestMapping("/home")
    public String homePage(Principal principal,
    								Model model,
    								@ModelAttribute("semester") String semester,
    								@ModelAttribute("semester_session_var") int ssv
    								){
    		String username = principal.getName();
    		User current_user = userService.findByUsername(username);
    		model.addAttribute("current_user", current_user);
       	Date userdate = current_user.getCreated_at();
       	SimpleDateFormat pattern1 = new SimpleDateFormat("yyyy");
		int user_created_at_year = Integer.parseInt(pattern1.format(userdate));
       	SimpleDateFormat pattern2 = new SimpleDateFormat("MM");
		int user_created_at_month = Integer.parseInt(pattern2.format(userdate));
       	SimpleDateFormat pattern3 = new SimpleDateFormat("dd");
		int user_created_at_day = Integer.parseInt(pattern3.format(userdate));
		String[] semester_season = {"Spring Semester " + user_created_at_year,
				"Fall Semester "  + user_created_at_year,
				"Spring Semester "  + (user_created_at_year+1),
				"Fall Semester " + (user_created_at_year+1)};
		if (user_created_at_month > 5) {
			if (user_created_at_day > DECEMBER_CUTOFF_DAY && user_created_at_month == DECEMBER) {
				semester_season[0] = "Spring Semester "  + (user_created_at_year+1);
				semester_season[1] = "Fall Semester "  + (user_created_at_year+1);
				semester_season[2] = "Spring Semester "  + (user_created_at_year+2);
				semester_season[3] = "Fall Semester "  + (user_created_at_year+2);
			}
			else {
				semester_season[0] = "Fall Semester "  + user_created_at_year;
				semester_season[1] = "Spring Semester "  + (user_created_at_year+1);
				semester_season[2] = "Fall Semester "  + (user_created_at_year+1);
				semester_season[3] = "Spring Semester "  + (user_created_at_year+2);

			}
		}
		model.addAttribute("semester_list", semester_season);
		List<Course> course_list = userService.findCoursesBySemester(semester_season[ssv]);
		model.addAttribute("course_list", course_list);
		//System.out.println("semester: " + ssv);
    		return "/WEB-INF/homePage.jsp";
    }
    
    @PostMapping("/home")
    public String semesterPick(@Valid @ModelAttribute("semester") String semester,
    		BindingResult result,
		@ModelAttribute("semester_session_var") int ssv,   								
    		Model model ) {

	    if (result.hasErrors()) {
	        return "/WEB-INF/homePage.jsp";
	    }
		//System.out.println("semester: " + semester);
		ssv = Integer.parseInt(semester);
		//System.out.println("semester session var: " + ssv);
		model.addAttribute("semester_session_var", ssv);
		return "redirect:/home";
    }

    @RequestMapping("/courses")
    public String coursePage(Principal principal,
    								Model model,
    								@ModelAttribute("semester") String semester,
    								@ModelAttribute("semester_session_var") int ssv
    								){
    		String username = principal.getName();
    		User current_user = userService.findByUsername(username);
    		model.addAttribute("current_user", current_user);
       	Date userdate = current_user.getCreated_at();
       	SimpleDateFormat pattern1 = new SimpleDateFormat("yyyy");
		int user_created_at_year = Integer.parseInt(pattern1.format(userdate));
       	SimpleDateFormat pattern2 = new SimpleDateFormat("MM");
		int user_created_at_month = Integer.parseInt(pattern2.format(userdate));
       	SimpleDateFormat pattern3 = new SimpleDateFormat("dd");
		int user_created_at_day = Integer.parseInt(pattern3.format(userdate));
		String[] semester_season = {"Spring Semester " + user_created_at_year,
				"Fall Semester "  + user_created_at_year,
				"Spring Semester "  + (user_created_at_year+1),
				"Fall Semester " + (user_created_at_year+1)};
		if (user_created_at_month > 5) {
			if (user_created_at_day > DECEMBER_CUTOFF_DAY && user_created_at_month == DECEMBER) {
				semester_season[0] = "Spring Semester "  + (user_created_at_year+1);
				semester_season[1] = "Fall Semester "  + (user_created_at_year+1);
				semester_season[2] = "Spring Semester "  + (user_created_at_year+2);
				semester_season[3] = "Fall Semester "  + (user_created_at_year+2);
			}
			else {
				semester_season[0] = "Fall Semester "  + user_created_at_year;
				semester_season[1] = "Spring Semester "  + (user_created_at_year+1);
				semester_season[2] = "Fall Semester "  + (user_created_at_year+1);
				semester_season[3] = "Spring Semester "  + (user_created_at_year+2);
			}
		}
		model.addAttribute("semester_list", semester_season);
		//System.out.println("semester: " + ssv);
		List<Course> course_list = userService.findCoursesBySemester(semester_season[ssv]);
		model.addAttribute("course_list", course_list);
    		return "/WEB-INF/coursePage.jsp";
    }

    @PostMapping("/courses")
    public String semesterPickFromCourse(@Valid @ModelAttribute("semester") String semester,
    		BindingResult result,
		@ModelAttribute("semester_session_var") int ssv,   								
    		Model model ) {

	    if (result.hasErrors()) {
	        return "/WEB-INF/coursePage.jsp";
	    }
		//System.out.println("semester: " + semester);
		ssv = Integer.parseInt(semester);
		//System.out.println("semester session var: " + ssv);
		model.addAttribute("semester_session_var", ssv);
		return "redirect:/courses";
    }

    @RequestMapping("/add/{id}")
	public String addCourse (Principal principal,
								Model model,
								@PathVariable("id") Long id) {
		String username = principal.getName();
		User current_user = userService.findByUsername(username);
		Course course_to_add = userService.findCourse(id);
		current_user.getCourses().add(course_to_add);
		userService.updateUser(current_user);
		return "redirect:/courses";
    }
    
    @RequestMapping("/drop/{id}")
	public String dropCourse (Principal principal,
								Model model,
								@PathVariable("id") Long id) {
		String username = principal.getName();
		User current_user = userService.findByUsername(username);
		Course course_to_drop = userService.findCourse(id);
		current_user.getCourses().remove(course_to_drop);
		userService.updateUser(current_user);
		return "redirect:/courses";
    }

    @RequestMapping("/drop2/{id}")
	public String drop2Course (Principal principal,
								Model model,
								@PathVariable("id") Long id) {
		String username = principal.getName();
		User current_user = userService.findByUsername(username);
		Course course_to_drop = userService.findCourse(id);
		current_user.getCourses().remove(course_to_drop);
		userService.updateUser(current_user);
		return "redirect:/home";
    }

    //following is needed for href link
    @RequestMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response,
    		SessionStatus session) { //added to clear session
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        session.setComplete(); // added to clear session
        return "redirect:/?logout";//You can redirect wherever you want,
        // but generally it's a good practice to show login screen again.
    }    

}
