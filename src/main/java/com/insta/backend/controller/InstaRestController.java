package com.insta.backend.controller;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insta.backend.DTO.BasicUserProfileDTO;
import com.insta.backend.entity.Post;
import com.insta.backend.entity.User;
import com.insta.backend.logging.Loggable;
import com.insta.backend.service.InstaRestService;

@RestController
@RequestMapping("/insta")
public class InstaRestController {
	
	@Autowired
	private InstaRestService instaRestService;
	
	@GetMapping("/postByUserId")
	public ResponseEntity<Set<Post>> postByUserId(@RequestParam Long userId){
		return ResponseEntity.ok(instaRestService.getPostById(userId));
	}
	
	@PostMapping("/uploadPost")
	public ResponseEntity<Post> uploadPost(@RequestBody Post post){
		return ResponseEntity.ok(instaRestService.uploadPost(post));
	}
	
	@Loggable
	@GetMapping("/updateConnections")
	public ResponseEntity<String> updateConnection(@RequestParam Long userId,@RequestParam Long conUseId, @RequestParam String actions){
		return ResponseEntity.ok(instaRestService.updateConncDet(userId, conUseId, actions));
	}
	
	@GetMapping("/feedByUserId")
	public ResponseEntity<List<Post>> feedByUserId(@RequestParam Long userId){
		return ResponseEntity.ok(instaRestService.getFeed(userId));
	}
	
	@GetMapping("/userProfileById")
	public ResponseEntity<User> userProfileById(@RequestParam Long userId){
		return ResponseEntity.ok(instaRestService.userProfileById(userId));
	}
	
	
	@GetMapping("/postRecByPostId")
	public ResponseEntity<String> postRecByPostId(@RequestParam Long postId, @RequestParam String reactionType){
		return ResponseEntity.ok(instaRestService.postRecByPostId(postId,reactionType));
	}
	
	@GetMapping("/deletePostById")
	public ResponseEntity<String> deletePostById(@RequestParam Long postId){
		return ResponseEntity.ok(instaRestService.deletePostById(postId));
	}
	
	@GetMapping(value="/followRec",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BasicUserProfileDTO>> followRec(@RequestParam Long userId){
		return ResponseEntity.ok(instaRestService.getFollowRec(userId));
	}
	
	@GetMapping
	public String testAPI() {
		return "Hello World";
	}

}
