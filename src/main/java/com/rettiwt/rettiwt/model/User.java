package com.rettiwt.rettiwt.model;

import com.rettiwt.rettiwt.model.Tweet;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique=true, nullable=false)
	private String login;

	@Column(nullable=false)
	private String password;
	
	@OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Tweet> tweets = new ArrayList<>();
	 
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addTweet(Tweet tweet) {
        tweets.add(tweet);
        tweet.setUser(this);
    }
 
    public void removeTweet(Tweet tweet) {
        tweets.remove(tweet);
        tweet.setUser(null);
    }
}
