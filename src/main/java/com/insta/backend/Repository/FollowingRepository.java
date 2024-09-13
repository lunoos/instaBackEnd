package com.insta.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insta.backend.entity.Following;
import com.insta.backend.entity.User;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Long>{
	
	@Modifying
	@Query("Delete Following e WHERE e.user = :user and e.followed = :conn")
	public void deleteFollowing(@Param("user") User user,@Param("conn") User conn);
	
	public List<Following> findByUser(User user);
	

}
