package com.rettiwt.rettiwt.service;

import com.rettiwt.rettiwt.model.Tweet;
import com.rettiwt.rettiwt.model.User;

import java.util.List;

public interface TweetService {
	List<Tweet> findAll();
	Tweet findById(long id);
	Tweet save(User user, Tweet tweet);
}
