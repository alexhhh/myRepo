package com.intern.alexx.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

	public void closeable(AutoCloseable ac) throws Exception {

		if (ac != null) {
			try {
				ac.close();
				ac = null;
			} catch (SQLException e) {
				logger.error(e.getMessage(), e.fillInStackTrace());
			}
		}
	}

}
