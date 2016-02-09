package com.intern.alexx.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;
import com.intern.alexx.repository.ReviewMesterRepository;

@Component
public class ReviewMesterRepositoryImp implements ReviewMesterRepository {

	@Autowired
	private DataSource dataSource;

	private Connection conn;

	public void insert(ReviewMester reviewMester) {

		String sql = "INSERT INTO review_mester (id_mester, id_client, rating, price, feedback) "
				+ "VALUES (?,?,?,?,?)";
		conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			setReviewMesterIntoDB(reviewMester, ps);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void update(ReviewMester reviewMester) {
		String sql = "UPDATE review_mester (id_mester, id_client, rating, price, feedback) " + "VALUES (?,?,?,?,?)";
		conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			setReviewMesterIntoDB(reviewMester, ps);
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	public void delete(ReviewMester reviewMester) {
		String sql = "DELETE FROM review_mester  WHERE id = ? ";
		conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewMester.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	public MyPage getAll(MesterSearchCriteria searchCriteria) {

		MyPage page = new MyPage();
		String sql = "SELECT COUNT(*) AS total FROM review_mester  ";
		try {
			page.setTotalRezults(prepareStatementForCountReviews(sql));
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		
		return page;
	}

	public int prepareStatementForCountReviews(String sql) throws SQLException {
		
		conn = null;
		int totalElements ;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			totalElements = resultSet.getInt("total");
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return totalElements;
	}

	public ReviewMester setReviewMesterIntoDB(ReviewMester reviewMester, PreparedStatement ps) throws SQLException {

		ps.setInt(1, reviewMester.getId());
		ps.setInt(2, reviewMester.getId());
		ps.setInt(3, reviewMester.getRating());
		ps.setString(4, reviewMester.getPrice());
		ps.setString(5, reviewMester.getFeedback());

		return reviewMester;
	}

}
