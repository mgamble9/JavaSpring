package com.gamble.admin_dashboard.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gamble.admin_dashboard.models.Role;
import com.gamble.admin_dashboard.models.User;
import com.gamble.admin_dashboard.repositories.RoleRepository;
import com.gamble.admin_dashboard.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
    // 1
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }
     
     // 2 
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }    
    
    public void updateUserWithLoginDate(User user) {
        userRepository.save(user);
    }    

    // 3
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
}
