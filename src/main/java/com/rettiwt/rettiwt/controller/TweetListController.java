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

import com.rettiwt.rettiwt.model.Tweet;
import com.rettiwt.rettiwt.model.User;
import com.rettiwt.rettiwt.service.TweetService;
import com.rettiwt.rettiwt.service.UserService;

@Scope(value = "session")
@ELBeanName(value = "tweetList")
@Component(value = "tweetList")
@Join(path = "/", to = "/tweet-list.jsf")
public class TweetListController {
	@Autowired
	private TweetService tweetService;
	
	private List<Tweet> tweets;
	
	@Autowired
	private UserService userService;
	
    private User loggedUser;

	@Deferred
	@RequestAction
	@IgnorePostback
	public void loadTweets() {
		loggedUser = userService.loggedUser();

		List<Tweet> allTweets = loggedUser.getTweets();
		for(User user : loggedUser.getFollowing()) {
			allTweets.addAll(user.getTweets());
		}

		allTweets.sort((t1, t2) -> t2.getTimestamp().compareTo(t1.getTimestamp()));
		tweets = allTweets;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}
}
