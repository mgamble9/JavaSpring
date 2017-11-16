package com.gamble.bnb.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.gamble.bnb.models.Role;

@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    @Size(min=1)
    private String first_name;
    @Size(min=1)
    private String last_name;
    @Size(min=5)
    private String password;
    @Pattern(regexp="^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")
    private String email;
    @Transient
    private String conf_password;
    @Transient
    private String role;
    private Date created_at;
    private Date updated_at;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    	private List<Role> roles;

    //1:many relationship with reviews
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Review> reviews;

    //1:many relationship with reviews
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Listing> listings;

	public User() {
    }
    public String getFull_name() {
		return first_name + " " + last_name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConf_password() {
		return conf_password;
	}
	public void setConf_password(String conf_password) {
		this.conf_password = conf_password;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public List<Listing> getListings() {
		return listings;
	}
	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}

    public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

    @PrePersist
    protected void onCreate(){
    this.setCreated_at(new Date());
    this.setUpdated_at(new Date());
    }

    @PreUpdate
    protected void onUpdate(){
    this.setUpdated_at(new Date());
    }

    
    

}