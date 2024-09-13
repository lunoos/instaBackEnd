package com.insta.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "FOLLOWING")
public class Following {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "following_seq")
    @SequenceGenerator(name = "following_seq", sequenceName = "following_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "followed_id")
    private User followed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFollowed() {
		return followed;
	}

	public void setFollowed(User followed) {
		this.followed = followed;
	}

	
	public Following(Long id, User user, User followed) {
		super();
		this.id = id;
		this.user = user;
		this.followed = followed;
	}

	public Following() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Following [id=" + id + ", user=" + user + ", followed=" + followed + "]";
	}

    
}
