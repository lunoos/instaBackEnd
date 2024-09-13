package com.insta.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insta.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	 public User getById(Long id);
	
	 @Modifying
	 @Query("UPDATE User e SET e.following = e.following + 1 WHERE e.id = :id")
	 public void incrementFollowing(@Param("id") Long id);
	 
	 @Modifying
	 @Query("UPDATE User e SET e.followers = e.followers + 1 WHERE e.id = :id")
	 public void incrementFollowers(@Param("id") Long id);
	 
	 @Modifying
	 @Query("UPDATE User e SET e.followers = e.followers - 1 WHERE e.id = :id")
	 public void decrementFollowers(@Param("id") Long id);
	 
	 @Modifying
	 @Query("UPDATE User e SET e.following = e.following - 1 WHERE e.id = :id")
	 public void decrementFollowing(@Param("id") Long id);
	 
	 public List<User> findByIdNotIn(List<Long> userId);
	 
}
