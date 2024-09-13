package com.insta.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insta.backend.entity.Post;
import com.insta.backend.entity.User;

public interface PostRepository extends JpaRepository<Post, Long>{

	public List<Post> findByUserIn(List<User> users);
	
	@Modifying
	@Query("update Post e set e.likes = e.likes+1 where id = :postId")
	public void increaseLikseById(@Param("postId") long postId);
	
	@Modifying
	@Query("update Post e set e.likes = e.likes-1 where id = :postId")
	public void decreaseLikseById(@Param("postId") long postId);
	
	
	
}
