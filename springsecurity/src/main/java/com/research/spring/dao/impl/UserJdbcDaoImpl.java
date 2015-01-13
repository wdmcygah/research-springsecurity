package com.research.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import com.research.spring.dao.IUserDao;
import com.research.spring.model.UserInfo;

public class UserJdbcDaoImpl extends JdbcDaoImpl implements IUserDao {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SaltSource saltSource;

	public void changePassword(String username, String password) {
		UserDetails user = loadUserByUsername(username);
		String encodedPassword = passwordEncoder.encodePassword(password,
				saltSource.getSalt(user));
		getJdbcTemplate().update(
				"UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?",
				encodedPassword, username);
	}

	@Override
	public UserDetails createUserDetails(String username,
			UserDetails userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities) {
		String returnUsername = userFromUserQuery.getUsername();
		if (!isUsernameBasedPrimaryKey()) {
			returnUsername = username;
		}
		return new UserInfo(returnUsername, userFromUserQuery.getPassword(),
				userFromUserQuery.isEnabled(), true, true, true,
				combinedAuthorities, ((UserInfo) userFromUserQuery).getSalt());
	}

	@Override
	public List<UserDetails> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(getUsersByUsernameQuery(),
				new String[] { username }, new RowMapper<UserDetails>() {
					public UserDetails mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String username = rs.getString(1);
						String password = rs.getString(2);
						boolean enabled = rs.getBoolean(3);
						String salt = rs.getString(4);
						return new UserInfo(username, password, enabled, true,
								true, true, AuthorityUtils.NO_AUTHORITIES, salt);
					}
				});
	}
}
