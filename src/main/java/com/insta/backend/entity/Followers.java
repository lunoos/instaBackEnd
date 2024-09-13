package com.insta.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "FOLLOWERS")
public class Followers {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "followers_seq")
    @SequenceGenerator(name = "followers_seq", sequenceName = "followers_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public User getFollower() {
		return follower;
	}



	public void setFollower(User follower) {
		this.follower = follower;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

	public Followers(Long id, User follower, User user) {
		super();
		this.id = id;
		this.follower = follower;
		this.user = user;
	}



	public Followers() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "Followers [id=" + id + ", follower=" + follower + ", user=" + user + "]";
	}

    
    
}
