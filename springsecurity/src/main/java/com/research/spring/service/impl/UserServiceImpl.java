package com.research.spring.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.memory.InMemoryDaoImpl;

public class UserServiceImpl extends InMemoryDaoImpl{

	public void changePassword(String username, String password) {
		// get the UserDetails
		User userDetails = 
		(User) getUserMap().getUser(username);
		// create a new UserDetails with the new password
		User newUserDetails = 
				new User(userDetails.getUsername(),password,
				userDetails.isEnabled(), 
				userDetails.isAccountNonExpired(),
				userDetails.isCredentialsNonExpired(),
				userDetails.isAccountNonLocked(),
				userDetails.getAuthorities());
				// add to the map
				getUserMap().addUser(newUserDetails);
				}
	}


