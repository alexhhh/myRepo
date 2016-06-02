package com.intern.alexx.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.Token;
import com.intern.alexx.repository.TokenRepository;

@Component
public class TokenRepositoryImp implements TokenRepository {

	private static Logger LOGGER = LoggerFactory.getLogger(TokenRepository.class);

	@Autowired
	private JdbcTemplate template;

	public void setJdbcTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public Token getById(String id) {
		Token token = new Token();
		String sql = "SELECT * FROM token WHERE id = ? ";
		template.query(sql, new Object[] { id }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				getTokenFromDB(token, rs);
			}
		});
		return token;
	}
	
	public Token getByUserName(String userName) {
		Token token = new Token();
		String sql = "SELECT * FROM token WHERE user_name = ? ";
		template.query(sql, new Object[] { userName }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				getTokenFromDB(token, rs);
			}
		});
		return token;
	}
	
	public Token insert(String  userName  ) {
		Token token = setToken( userName);
		String sql = "INSERT INTO token (ID, USER_NAME, EXP_DATE) " + "VALUES (?,?,? )";
		template.update(sql, new Object[] { token.getId(), token.getUserName(), token.getExpirationDate() });
		LOGGER.info("---token---" + token.toString());
		return token;
	}

	public void delete(String id) {
		String sql = "DELETE FROM token  WHERE id = ? ";
		template.update(sql, id);
	}
 
	private Token getTokenFromDB(Token token, ResultSet resultSet) throws SQLException {
		token.setId(resultSet.getString("id"));
		token.setUserName(resultSet.getString("user_name"));
		token.setExpirationDate(resultSet.getDate("exp_date"));
		token.setPassword(resultSet.getString("password"));
		return token;
	}

	private Token setToken(String userName) {
		Token token = new Token();
		token.setId(GUIDGenerator.generatedID());
		java.sql.Date expDate = new java.sql.Date(Calendar.getInstance().getTime().getTime() + 864000000); // o zi																											 
		token.setExpirationDate(expDate);
		token.setUserName(userName);
		return token;
	}

}
