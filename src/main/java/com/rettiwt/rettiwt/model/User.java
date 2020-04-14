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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique=true, nullable=false)
	private String username;

	@Column(nullable=false)
	private String password;

	@OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Tweet> tweets = new ArrayList<>();
	 
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "user_following_user",
        joinColumns = @JoinColumn(name = "followed_id"),
		inverseJoinColumns = @JoinColumn(name = "follower_id")
	)
	List<User> followers;

	@ManyToMany(mappedBy = "followers")
	List<User> following;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public List<Tweet> getTweets() {
		return tweets;
	}
	
	public void addFollower(User follower) {
		if(this.id != follower.getId()) {
			followers.add(follower);
			follower.following.add(this);
		}
	}

	public void addFollowing(User followed) {
		followed.addFollower(this);
	}

	public void removeFollower(User unfollower) {
		if(this.id != unfollower.getId()) {
			followers.remove(unfollower);
			unfollower.following.remove(this);
		}
	}

	public void removeFollowing(User unfollowed) {
		unfollowed.removeFollower(this);
	}

	public List<User> getFollowers() {
		return followers;
	}

	public List<User> getFollowing() {
		return following;
	}

	public int getFollowersAmount() {
		return followers.size();
	}

	public int getFollowingAmount() {
		return following.size();
	}

	public boolean equals(Object o) {
		if (!(o instanceof User)) {
			return false;
		}

		User other = (User) o;
		return username.equals(other.username) && id == other.id && password.equals(other.password);
	}
	  
	public int hashCode() {
		return username.hashCode();
	}
}
