package com.rettiwt.rettiwt.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.rettiwt.rettiwt.model.Tweet;
import com.rettiwt.rettiwt.repository.TweetRepository;

@Scope
@ELBeanName(value = "tweetController")
@Component(value = "tweetController")
@Join(path = "/tweet", to = "/tweet-form.jsf")
public class TweetController {
	
	@Autowired
	private TweetRepository tweetRepository;
	
	private Tweet tweet = new Tweet();

	public String save() {
		tweetRepository.save(tweet);
		tweet = new Tweet();
		return "/tweet-list.xhtml?faces-redirect=true";
	}

	public Tweet getTweet() {
		return tweet;
	}
}
