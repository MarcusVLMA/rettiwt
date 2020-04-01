package com.rettiwt.rettiwt.service;

import com.rettiwt.rettiwt.model.Tweet;
import com.rettiwt.rettiwt.model.User;

import java.util.List;

public interface UserService {
	List<User> findAll();
	User findById(long id);
	User save(User user);
	
	Tweet saveTweet(User user, Tweet tweet);
}
