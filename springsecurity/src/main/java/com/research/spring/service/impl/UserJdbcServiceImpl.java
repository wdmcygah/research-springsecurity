package com.research.spring.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import com.research.spring.service.IUserService;

public class UserJdbcServiceImpl implements IUserService{

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public void changePassword(String username, String password) {
		// TODO Auto-generated method stub
		
	}

}
