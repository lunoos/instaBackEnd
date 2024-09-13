package com.insta.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.insta.backend.strategy.FeedStratergy;

import jakarta.annotation.PostConstruct;

@Configuration
public class InstaAppConfig {

	@Autowired
    private List<FeedStratergy> feedStratergies;
	
	
	//@DependsOn("LatestFeedStratergy")
	 @Bean
	    public Map<String, FeedStratergy> feedStatMap() {
		 Map<String, FeedStratergy> implementationMap = feedStratergies.stream().collect(Collectors.toMap(FeedStratergy::getStatName, stat -> stat));
		 return implementationMap;
	 }
}
