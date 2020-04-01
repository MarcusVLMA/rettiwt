package com.rettiwt.rettiwt.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rettiwt.rettiwt.model.Tweet;
import com.rettiwt.rettiwt.model.User;
import com.rettiwt.rettiwt.repository.TweetRepository;
import com.rettiwt.rettiwt.repository.UserRepository;

@Component
public class Seeds {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TweetRepository tweetRepository;

	// Comentar "@PostConstruct" para não ser executado todas as vezes
	// @PostConstruct 
	public void initialData() {
		List<User> 	userList = new ArrayList<>();
		
		User user1 = new User();
		user1.setLogin("neymarjr");
		user1.setPassword("123456");
		
		User user2 = new User();
		user2.setLogin("tiosergio");
		user2.setPassword("654321");
		
		Tweet tweet1 = new Tweet();
		tweet1.setText("Tô chegando com os refri rapaziada");
		
		Tweet tweet2 = new Tweet();
		tweet2.setText("O ousado chegouuu !!! kkkkk");

		Tweet tweet3 = new Tweet();
		tweet3.setText("Deus é TOP");

		Tweet tweet4 = new Tweet();
		tweet4.setText("bOA noite todos amigo");
		
		user1.addTweet(tweet1);
		user1.addTweet(tweet2);
		user1.addTweet(tweet3);
		
		user2.addTweet(tweet4);
		
		userList.add(user1); userList.add(user2);
		
		for(User user: userList) {
			try {
				User userSaved = userRepository.save(user);
				System.out.println("User criado com ID: " + userSaved.getId());
			} catch(Exception e) {
				System.out.println("Erro ao criar user: " + e.getMessage());
			}
		}
	}
}
