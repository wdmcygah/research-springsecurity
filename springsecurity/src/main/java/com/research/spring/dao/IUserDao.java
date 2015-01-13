package com.research.spring.dao;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserDao {

	void changePassword(String username, String password);
	
	UserDetails createUserDetails(String username,			UserDetails userFromUserQuery,			List<GrantedAuthority> combinedAuthorities);
	
	List<UserDetails> loadUsersByUsername(String username);
}
