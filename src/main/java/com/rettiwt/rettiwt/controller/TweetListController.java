package com.rettiwt.rettiwt.controller;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rettiwt.rettiwt.model.Tweet;
import com.rettiwt.rettiwt.repository.TweetRepository;
import com.rettiwt.rettiwt.service.TweetService;

@Scope(value = "session")
@ELBeanName(value = "tweetList")
@Component(value = "tweetList")
@Join(path = "/", to = "/tweet-list.jsf")
public class TweetListController {
	@Autowired
	private TweetRepository tweetRepository;
	
	private List<Tweet> tweets;

	@Deferred
	@RequestAction
	@IgnorePostback
	public void loadTweets() {
		tweets = tweetRepository.findAll();
	}

	public List<Tweet> getTweets() {
		return tweets;
	}
}
