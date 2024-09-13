package com.insta.backend.strategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.backend.Repository.FollowingRepository;
import com.insta.backend.Repository.PostRepository;
import com.insta.backend.Repository.UserRepository;
import com.insta.backend.entity.Following;
import com.insta.backend.entity.Post;
import com.insta.backend.entity.User;

@Service
public class LatestFeedStratergy implements FeedStratergy{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FollowingRepository followingRepository;
	
	
	@Override
	public List<Post> getFeed(Long userId) {
		User user = userRepository.getById(userId);
		List<Following> followingList = followingRepository.findByUser(user);
		List<User> userIds = followingList.stream().map(x -> x.getFollowed()).collect(Collectors.toList());
		List<Post> posts = postRepository.findByUserIn(userIds);
		Collections.sort(posts,(x,y)->(int)(y.getId()-x.getId()));
		return posts;
	}


	@Override
	public String getStatName() {
		return "LatestFeed";
	}
	
	
	
}
