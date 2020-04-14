package com.rettiwt.rettiwt.controller;

import java.util.List;

import com.rettiwt.rettiwt.model.User;

import com.rettiwt.rettiwt.service.UserService;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@ELBeanName(value = "userList")
@Component(value = "userList")
@Join(path = "/user/list", to = "/user-list.jsf")

public class UserListController {
    @Autowired
	private UserService userService;
	
	private List<User> users;
    private User loggedUser;

	@Deferred
	@RequestAction
	@IgnorePostback
	public void loadUsers() {
        this.loggedUser = userService.loggedUser();

        this.users = userService.findAll();
        users.remove(this.loggedUser);
	}

	public boolean renderFollowButton(User user) {
		return this.loggedUser.getFollowing().contains(user);
	}

	public List<User> getUsers() {
		return this.users;
	}
	
	public User getLoggedUser() {
		return this.loggedUser;
	}
    
    public String follow(User user) {
		user.addFollower(this.loggedUser);
		userService.save(user);
        return "/user/list";
	}
	
	public String unfollow(User user) {
		user.removeFollower(this.loggedUser);
		userService.save(user);
		return "/user/list"; 
	}
}