package com.rettiwt.rettiwt.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.rettiwt.rettiwt.model.User;
import com.rettiwt.rettiwt.repository.UserRepository;
import com.rettiwt.rettiwt.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> findByUsername(String username) {	
		return userRepository.findByUsername(username);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User loggedUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser;
        
		if(principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
			loggedUser = userRepository.findByUsername(username).get();
		} else {
			loggedUser = userRepository.findByUsername(principal.toString()).get();
        }
		
		return loggedUser;
	}
}
