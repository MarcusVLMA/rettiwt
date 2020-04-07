package com.rettiwt.rettiwt.service;

import com.rettiwt.rettiwt.model.Tweet;
import com.rettiwt.rettiwt.model.User;

import java.util.List;

public interface UserService {
	public List<User> findAll();
	public User findById(long id);
	public User findByUsername(String username);
	public User save(User user);
	
	public Tweet saveTweet(User user, Tweet tweet);
}
