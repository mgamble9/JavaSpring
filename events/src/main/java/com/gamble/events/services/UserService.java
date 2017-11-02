package com.gamble.events.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gamble.events.models.Comment;
import com.gamble.events.models.Event;
import com.gamble.events.models.Role;
import com.gamble.events.models.User;
import com.gamble.events.repositories.CommentRepository;
import com.gamble.events.repositories.EventRepository;
import com.gamble.events.repositories.RoleRepository;
import com.gamble.events.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private CommentRepository commentRepository;
    private EventRepository eventRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository,
    						RoleRepository roleRepository,
    						BCryptPasswordEncoder bCryptPasswordEncoder,
    						EventRepository eventRepository,
    						CommentRepository commentRepository)     {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.eventRepository = eventRepository;
        this.commentRepository = commentRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }
     
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }    
    
    public void updateUserWithLoginDate(User user) {
        userRepository.save(user);
    }    

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public List<User> allUsers() {
		return (List<User>) userRepository.findAll();
    }

    public void destroyUser(Long id) {
    		userRepository.delete(id);
    }


	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	public List<Role> findRoleByName(String name) {
		return roleRepository.findByName(name);
	}


	public void updateUserWithAdminRole(User user) {
        userRepository.save(user);
	}


	public void saveEvent(Event event) {
		eventRepository.save(event);
	}
	
	public List<Event> allEventsInState(String state) {
		return eventRepository.findByState(state);
	}
	
	public List<Event> allEventsNotInState(String state) {
		return eventRepository.findAllNotInState(state);
	}


	public void destroyEvent(Long id) {
		eventRepository.delete(id);
	}


	public Event findEventById(Long id) {
		return eventRepository.findOne(id);
	}


	public void saveComment(Comment comment) {
		commentRepository.save(comment);
	}

}
