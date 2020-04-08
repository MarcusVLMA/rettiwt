package com.rettiwt.rettiwt.service;

import com.rettiwt.rettiwt.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
	public List<User> findAll();
	public Optional<User> findById(long id);
	public Optional<User> findByUsername(String username);
	public User save(User user);
}
