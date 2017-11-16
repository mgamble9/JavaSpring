package com.gamble.bnb.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="reviews")
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    @Size(min=1)
    private String review_txt;
    @Min(1)
    @Max(5)
    private int rating;
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date created_at;   
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date updated_at;
    //many:1 relationship with user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    //many:1 relationship with listing
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="listing_id")
    private Listing listing;
	
    public Review() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReview_txt() {
		return review_txt;
	}

	public void setReview_txt(String review_txt) {
		this.review_txt = review_txt;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
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
