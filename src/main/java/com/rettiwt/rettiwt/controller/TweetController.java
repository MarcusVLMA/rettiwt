package com.rettiwt.rettiwt.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.rettiwt.rettiwt.model.Tweet;
import com.rettiwt.rettiwt.model.User;
import com.rettiwt.rettiwt.service.TweetService;
import com.rettiwt.rettiwt.service.UserService;

@Scope
@ELBeanName(value = "tweetController")
@Component(value = "tweetController")
@Join(path = "/tweet", to = "/tweet-form.jsf")
public class TweetController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TweetService tweetService;

	private Tweet tweet = new Tweet();


	public String save() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user;
		if(principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
			user = userService.findByUsername(username).get();
		} else {
			user = userService.findByUsername(principal.toString()).get();
		}

		tweetService.save(user, tweet);
		tweet = new Tweet();
		return "/tweet-list.xhtml?faces-redirect=true";
	}

	public Tweet getTweet() {
		return tweet;
	}
}
