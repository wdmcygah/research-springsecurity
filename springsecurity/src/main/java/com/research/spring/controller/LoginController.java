package com.research.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.research.spring.dao.IUserDao;

@Controller
public class LoginController {

	@Autowired
	@Qualifier("userJdbcDaoImpl")
	private IUserDao userDao;

	@RequestMapping("/login.htm")
	public void index() {
	}

	@RequestMapping("/password.htm")
	public String password() {
		return "/password";
	}
	
	@RequestMapping("/updatePwd.htm")
	public String updatePwd(@RequestParam("password") String newPassword) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String username = principal.toString();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}
		userDao.changePassword(username, newPassword);
		SecurityContextHolder.clearContext();
		return "redirect:login.htm";
	}
}
