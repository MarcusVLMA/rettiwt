package com.rettiwt.rettiwt.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rettiwt.rettiwt.model.Tweet;
import com.rettiwt.rettiwt.model.User;
import com.rettiwt.rettiwt.repository.TweetRepository;
import com.rettiwt.rettiwt.repository.UserRepository;
import com.rettiwt.rettiwt.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TweetRepository tweetRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public Tweet saveTweet(User user, Tweet tweet) {
		user.addTweet(tweet);
		return tweetRepository.save(tweet);
	}
}
