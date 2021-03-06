package com.rettiwt.rettiwt.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.rettiwt.rettiwt.model.User;
import com.rettiwt.rettiwt.service.UserService;

@Scope
@ELBeanName(value = "signUpController")
@Component(value = "signUpController")
@Join(path = "/signup", to = "/signup-form.jsf")
public class SignUpController {
	
	@Autowired
	private UserService userService;

	private User user = new User();

	
	public String save() {
        Optional<User> userExists = userService.findByUsername(user.getUsername());
		FacesContext context = FacesContext.getCurrentInstance();
		if(userExists.isPresent()) {
			context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ops!","Username already exists"));
			return "/signup-form.xhtml?username-exists=true";    
        }
		userService.save(user);
		
		context.addMessage("growl", new FacesMessage("Successful", "Succesfully signed up"));
		context.getExternalContext().getFlash().setKeepMessages(true);
		return "/login.xhtml";
	}

	public User getUser() {
		return user;
	}
}
