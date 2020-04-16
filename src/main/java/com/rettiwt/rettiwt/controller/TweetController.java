package com.rettiwt.rettiwt.controller;

import java.sql.Timestamp;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(tweet.getText() == null || tweet.getText().isEmpty()) {
			context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "What?","You can't send a empty tweet"));
			
			return "/tweet-form.xhtml?empty-twitter=true";
		} else {
			User loggedUser = userService.loggedUser();

			tweet.setTimestamp(new Timestamp(System.currentTimeMillis()) );
			tweetService.save(loggedUser, tweet);
			tweet = new Tweet();
			
			context.addMessage("growl", new FacesMessage("Nice!", "Succesfully tweeted!"));
			context.getExternalContext().getFlash().setKeepMessages(true);

			return "/tweet-list.xhtml?faces-redirect=true";
		}
	}

	public Tweet getTweet() {
		return tweet;
	}
}
