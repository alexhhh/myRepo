package com.intern.alexx.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException; 
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
		String sql = "SELECT * FROM user WHERE user_name = ?  AND password=?";

		User user = template.query(sql, new Object[] { userName, password }, new ResultSetExtractor<User>() {
			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				User user = null;
				if (rs.next()) {
					user = new User();
					getUserFromDB(user, rs);
					LOGGER.info("---user-up--" + user.toString());
				}
				return user;
			}
		});
		
		if (user == null){
			//todo throw exception
		}

		return user;
	}

	public User getUserByUserName(String userName) {
		User user = new User();
		String sql = "SELECT * FROM user WHERE user_name = ? ";
		template.query(sql, new Object[] { userName }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				getUserFromDB(user, rs);
				LOGGER.info("---user--u-" + user.toString());
			}
		});
		return user;
	}
	
	public User getUserById(String id) {
		User user = new User();
		String sql = "SELECT * FROM user WHERE id = ? ";
		template.query(sql, new Object[] { id }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				getUserFromDB(user, rs);
				LOGGER.info("---user--id-" + user.toString());
			}
		});
		return user;
	}

	public void insertUser(User user) {
		user.setId(GUIDGenerator.generatedID());
		user.setEnable(0);
		String sql = "INSERT INTO user (ID, USER_NAME, PASSWORD, EMAIL, ROLE_ID, ENABLE) " + "VALUES (?,?,?,?,?, ?)";
		template.update(sql, new Object[] { user.getId(), user.getUserName(), user.getPassword(), user.getEmail(),
				user.getRoleId(), user.isEnable() });
		LOGGER.info("---user---" + user.toString());
	}

	public void updateUser(User user) {
		String sql = "UPDATE  user SET USER_NAME= ?,PASSWORD=?, EMAIL= ?, ROLE_ID=?, ENABLE=?  WHERE id = ?";
		template.update(sql, new Object[] { user.getUserName(), user.getPassword(), user.getEmail(), user.getRoleId(),
				user.isEnable(), user.getId() });
	}
	
	public void updateUserDetails(User user) {
		String sql = "UPDATE  user SET PASSWORD= ?  WHERE id = ?";
		template.update(sql, new Object[] { user.getPassword(), user.getId() });
	}
	
	public void updateUserEmail(User user) {
		String sql = "UPDATE  user SET EMAIL= ?  WHERE id = ?";
		template.update(sql, new Object[] { user.getEmail(), user.getId() });
	}

	public void deleteUser(String id) {
		String sql = "DELETE FROM user  WHERE id = ? ";
		template.update(sql, id);
	}

	public String getUserRole(int roleId) {
		String sql = "SELECT role FROM role  WHERE id= ? ";
		return template.queryForObject(sql, new Object[] { roleId }, String.class);
	}

	private User getUserFromDB(User user, ResultSet resultSet) throws SQLException {
		user.setId(resultSet.getString("id"));
		user.setUserName(resultSet.getString("user_name"));
		user.setPassword(resultSet.getString("password"));
		user.setEmail(resultSet.getString("email"));
		user.setRoleId(resultSet.getInt("role_id"));
		user.setEnable(resultSet.getInt("enable"));
		return user;
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		String sql ="SELECT * FROM user ORDER BY role_id";
		List<User> users = new ArrayList<User>();		 
 
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<String, Object> row : rows) {
			User user = new User();
			userRowMapper(user, row);
			users.add(user);
		}		
		return users;
	}
	
	
	private User userRowMapper(User user, Map<String, Object> row) throws SQLException {
		user.setId((String) (row.get("id")));
		user.setUserName((String) (row.get("user_name")));
		user.setPassword((String) (row.get("password")));
		user.setEmail((String) (row.get("email")));
		user.setRoleId((Integer) (row.get("role_id")));
		user.setEnable((Integer) (row.get("enable")));		 
		return user;
	}

}
