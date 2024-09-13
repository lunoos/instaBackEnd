package com.insta.backend.strategy;

import java.util.List;

import com.insta.backend.entity.Post;

public interface FeedStratergy {
	public List<Post> getFeed(Long userId);
	public String getStatName();
}
