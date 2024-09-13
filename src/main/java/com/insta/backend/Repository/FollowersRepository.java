package com.insta.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insta.backend.entity.Followers;
import com.insta.backend.entity.User;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, Long> {
	
	@Modifying
	@Query("Delete Followers e WHERE e.user = :user and e.follower = :conn")
	public void deleteFollower(@Param("user") User user,@Param("conn") User conn);
}
