package com.intern.alexx.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.User;
import com.intern.alexx.repository.UserRepository;

@Component
public class UserRepositoryImp implements UserRepository {

	private static Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

	@Autowired
	private JdbcTemplate template;

	public void setJdbcTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public User getUserByCredentials(String userName, String password) {

		User user = new User();
		String sql = "SELECT * FROM user WHERE user_name = ?  AND password=?";
		template.query(sql, new Object[] { userName, password }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				getUserFromDB(user, rs);
			}
		});
		//String userToken = "Basic " + org.springframework.security.crypto.codec.Base64.(userName + ":" + password);
		LOGGER.info("---user---" + user.toString());
		return user;
	}
	
	public User getUserByUserName(String userName) {

		User user = new User();
		String sql = "SELECT * FROM user WHERE user_name = ? ";
		template.query(sql, new Object[] { userName }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				getUserFromDB(user, rs);
			}
		});
		LOGGER.info("---user---" + user.toString());
		return user;
	}

	public void insertUser(User user) {
		user.setId(GUIDGenerator.generatedID());
		user.setEnable(false);
		String sql = "INSERT INTO user (ID, USER_NAME, PASSWORD, EMAIL, ROLE_ID, ENABLE) " + "VALUES (?,?,?,?,?, ?)";
		template.update(sql, new Object[] { user.getId(), user.getUserName(), user.getPassword(), user.getEmail(),
				user.getRoleId(), user.isEnable() });
		LOGGER.info("---user---" + user.toString());
	}

	public void updateUser(User user) {
		String sql = "UPDATE  contact SET USER_NAME= ?,PASSWORD=?, EMAIL= ?, ROLE_ID=?, ENABLE=?  WHERE id = ?";
		template.update(sql, new Object[] { user.getUserName(), user.getPassword(), user.getEmail(), user.getRoleId(),
				user.isEnable(), user.getId() });
	}

	public void deleteUser(String id) {
		String sql = "DELETE FROM user  WHERE id = ? ";
		template.update(sql, id);
	}

	public String getUserRole (int roleId){
		String sql = "SELECT role FROM role  WHERE id= ? ";
		return template.queryForObject(sql, new Object[] { roleId }, String.class);
	}
	
	private User getUserFromDB(User user, ResultSet resultSet) throws SQLException {
		user.setId(resultSet.getString("id"));
		user.setUserName(resultSet.getString("user_name"));
		user.setPassword(resultSet.getString("password"));
		user.setEmail(resultSet.getString("email"));
		user.setRoleId(resultSet.getInt("role_id"));
		user.setEnable(resultSet.getBoolean("enable"));
		return user;
	}

}
