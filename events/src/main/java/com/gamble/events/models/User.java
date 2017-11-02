package com.gamble.events.models;

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

@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    @Size(min=1)
    private String firstName;
    @Size(min=1)
    private String lastName;
    @Size(min=5)
    private String password;
    @Pattern(regexp="^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")
    private String email;
    @Transient
    private String passwordConfirmation;
    private Date createdAt;
    private Date updatedAt;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    	private List<Role> roles;

    //1:many relationship with events adding host
    @OneToMany(mappedBy="host", fetch = FetchType.LAZY)
    	private List<Event> hosted_events;
    
    //many:many relationship with events
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "events_users",
            joinColumns = @JoinColumn(name = "user_id"), 
            inverseJoinColumns = @JoinColumn(name = "event_id")
    		)
    	private List<Event> events_attending;

    //1:many relationship with comments
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Comment> comments;

    
    @Size(min=1)
    private String city;
    @Size(min=1)
    private String state;

    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User() {
    }
    public String getFullName() {
		return firstName + " " + lastName;
	}

    public String getLocation() {
		return city + ", " + state;
	}

    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    @PrePersist
    protected void onCreate(){
    this.setCreatedAt(new Date());
    this.setUpdatedAt(new Date());
    }

    @PreUpdate
    protected void onUpdate(){
    this.setUpdatedAt(new Date());
    }

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Event> getEvents_attending() {
		return events_attending;
	}

	public void setEvents_attending(List<Event> events_attending) {
		this.events_attending = events_attending;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Event> getHosted_events() {
		return hosted_events;
	}

	public void setHosted_events(List<Event> hosted_events) {
		this.hosted_events = hosted_events;
	}
}
