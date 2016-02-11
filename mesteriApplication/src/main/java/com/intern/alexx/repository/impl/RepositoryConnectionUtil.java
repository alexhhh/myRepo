package com.intern.alexx.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryConnectionUtil {

	@Autowired
	private DataSource dataSource;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(MesterRepositoryImp.class);
	
	public PreparedStatement prepareConnection(Connection conn, String sql) throws SQLException {

		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		return ps;
	}

	public void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {

		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				logger.error(e.getMessage(), e.fillInStackTrace());
			}
		}

		if (ps != null) {
			try {
				ps.close();
				ps = null;
			} catch (SQLException e) {
				logger.error(e.getMessage(), e.fillInStackTrace());
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e.fillInStackTrace());
			}
		}

	}
	
}
