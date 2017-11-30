package com.gamble.courseSignup.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gamble.courseSignup.models.Course;
import com.gamble.courseSignup.models.Role;
import com.gamble.courseSignup.models.User;
import com.gamble.courseSignup.repositories.CourseRepository;
import com.gamble.courseSignup.repositories.RoleRepository;
import com.gamble.courseSignup.repositories.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private CourseRepository courseRepository;
    
    public UserService(UserRepository userRepository,
			RoleRepository roleRepository,
		BCryptPasswordEncoder bCryptPasswordEncoder,
		CourseRepository courseRepository) {
	super();
	this.userRepository = userRepository;
	this.courseRepository = courseRepository;
	this.roleRepository = roleRepository;
	this.bCryptPasswordEncoder = bCryptPasswordEncoder;
}

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }
    
    public void updateUser(User user) {
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}

	public Role findRoleByName(String name) {
		return roleRepository.findOneByName(name);
	}

	public List<Course> findCoursesBySemester(String semester) {
		return courseRepository.findBySemesterContaining(semester);
	}

	public Course findCourse(Long id) {
		return courseRepository.findOne(id);
	}


}
