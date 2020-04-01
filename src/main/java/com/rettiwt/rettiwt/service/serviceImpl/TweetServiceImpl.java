package com.rettiwt.rettiwt.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rettiwt.rettiwt.model.Tweet;
import com.rettiwt.rettiwt.repository.TweetRepository;
import com.rettiwt.rettiwt.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	TweetRepository tweetRepository;
	
	@Override
	public List<Tweet> findAll() {
		return tweetRepository.findAll();
	}

	@Override
	public Tweet findById(long id) {
		return tweetRepository.findById(id).get();
	}

}
