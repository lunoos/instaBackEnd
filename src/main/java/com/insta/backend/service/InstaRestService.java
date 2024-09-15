package com.insta.backend.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.backend.DTO.BasicUserProfileDTO;
import com.insta.backend.Repository.FollowersRepository;
import com.insta.backend.Repository.FollowingRepository;
import com.insta.backend.Repository.PostRepository;
import com.insta.backend.Repository.UserRepository;
import com.insta.backend.entity.Followers;
import com.insta.backend.entity.Following;
import com.insta.backend.entity.Post;
import com.insta.backend.entity.User;
import com.insta.backend.exception.ResourceNotFoundException;
import com.insta.backend.strategy.FeedStratergy;

import jakarta.transaction.Transactional;

@Service
public class InstaRestService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private FollowersRepository followersRepository;

	@Autowired
	private FollowingRepository followingRepository;

	@Autowired
	private Map<String, FeedStratergy> feedStatMap;

//	@Value("${myapp.implementation.name}")
//    private String selectedFeedStatName;

	public Set<Post> getPostById(final Long userId) {
		User user = userRepository.getById(userId);
		return user.getPosts();
	}

	public Post uploadPost(final Post post) {
		Post savedPost = postRepository.save(post);
		return savedPost;
	}

	@Transactional
	public String updateConncDet(final Long userId, final Long connId, final String action) {
		User user = userRepository.getById(userId);
		User conn = userRepository.getById(connId);
		if (action.equalsIgnoreCase("Follow")) {
			Followers followers = new Followers();
			followers.setFollower(user);
			followers.setUser(conn);
			followersRepository.save(followers);
			Following following = new Following();
			following.setFollowed(conn);
			following.setUser(user);
			followingRepository.save(following);
			userRepository.incrementFollowers(connId);
			userRepository.incrementFollowing(userId);
		} else {
			followersRepository.deleteFollower(conn, user);
			followingRepository.deleteFollowing(user, conn);
			userRepository.decrementFollowers(connId);
			userRepository.decrementFollowing(userId);
		}
		return "Success";
	}

	/**
	 * Method to return the list of post of user whom the longed in user followed.
	 * With latest post appearing on the top.
	 * 
	 * @param userId
	 * @return
	 */
	public List<Post> getFeed(Long userId) {
		List<Post> result = null;
		try {
			result = feedStatMap.get("latestFeedStratergy").getFeed(userId);
			if(result!=null)
				throw new ResourceNotFoundException("Error while fetcing user feed");
		} catch (Exception e) {
			throw new ResourceNotFoundException("Error while fetcing user feed");
		}
		return result;
	}

	public User userProfileById(final Long userId) {
		return userRepository.getById(userId);
	}

	/**
	 * Method to capture the reaction on a post.
	 * @param postId
	 * @param reactionType
	 * @return
	 */
	public String postRecByPostId(final Long postId, final String reactionType) {
		if (reactionType.equalsIgnoreCase("Like"))
			postRepository.increaseLikseById(postId);
		else if (reactionType.equalsIgnoreCase("DisLike"))
			postRepository.decreaseLikseById(postId);
		return "Success";
	}
	
	public String deletePostById(final Long postId) {
		postRepository.deleteById(postId);
		return "Success";
	}
	
	public List<BasicUserProfileDTO> getFollowRec(final Long userId){
		//Can implement strategy pattern using recommendation strategy dynamically
		//Get all the user excep which i am already following.
		User user = userRepository.getById(userId);
		List<Following> followingList = followingRepository.findByUser(user);
		List<Long> followingListIds = followingList.stream().map(x->x.getFollowed().getId()).collect(Collectors.toList());
		followingListIds.add(userId);
		List<User> userList = userRepository.findByIdNotIn(followingListIds);
		List<BasicUserProfileDTO> result = userList.stream().map(x ->new BasicUserProfileDTO(x.getId(),x.getName())).collect(Collectors.toList());
		return result;
		
	}
}
