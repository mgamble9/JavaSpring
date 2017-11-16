package com.gamble.bnb.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gamble.bnb.models.Listing;
import com.gamble.bnb.models.Review;
import com.gamble.bnb.models.Role;
import com.gamble.bnb.models.User;
import com.gamble.bnb.repositories.ListingRepository;
import com.gamble.bnb.repositories.ReviewRepository;
import com.gamble.bnb.repositories.RoleRepository;
import com.gamble.bnb.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private ListingRepository listingRepository;
    private ReviewRepository reviewRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    public UserService(UserRepository userRepository,
    			RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			ListingRepository listingRepository,
			ReviewRepository reviewRepository) {
		super();
		this.userRepository = userRepository;
		this.listingRepository = listingRepository;
		this.reviewRepository = reviewRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
    
    public void saveUserWithGuestRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_GUEST"));
        userRepository.save(user);
    }
     
    public void saveUserWithHostRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_HOST"));
        userRepository.save(user);
    }    
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}

	public Role findRoleByName(String name) {
		return roleRepository.findOneByName(name);
	}

	public void saveListing(Listing listing) {
		listingRepository.save(listing);
	}

	public List<Listing> searchListings(String search_str) {
		return listingRepository.findByAddressContaining(search_str);
	}

	public Listing findListing(Long id) {
		return listingRepository.findOne(id);
	}

	public void saveReview(Review review) {
		reviewRepository.save(review);
		
	}

	public void updateListing(Listing listing_new) {
		listingRepository.save(listing_new);
	}
    
}
