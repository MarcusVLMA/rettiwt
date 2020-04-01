package com.rettiwt.rettiwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rettiwt.rettiwt.model.Tweet;
import com.rettiwt.rettiwt.service.TweetService;

@Controller
public class TweetController {
	
	@Autowired
	TweetService tweetService;
	
	@RequestMapping(value = "/tweets/all", method = RequestMethod.GET)
	public ModelAndView getAllTweets() {
		ModelAndView mv = new ModelAndView("allTweets");
		List<Tweet> allTweets = tweetService.findAll();
		mv.addObject("allTweets", allTweets);
		
		return mv;
	}
}
