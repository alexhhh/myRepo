package com.intern.alexx.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		String sql = "UPDATE review_mester SET id_mester=?, id_client=?, rating=?, price=?, feedback=? WHERE id=?";
		conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			setReviewMesterIntoDB(reviewMester, ps);
			ps.setInt(6, reviewMester.getId());
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

	public ReviewMester getById(ReviewMester reviewMester) {
		conn=null;
		String sql = "SELECT * FROM review_mester WHERE id = ?";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewMester.getId());
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				getReviewFromDB(resultSet);
			}
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

		return reviewMester;
	}
	
	public MyPage<ReviewMester> getAll(MesterSearchCriteria searchCriteria) {
		List<ReviewMester> reviews = new ArrayList<ReviewMester>();
		String sql2 = "SELECT * FROM review_mester AS rm LIMIT ";
		String sql = "SELECT COUNT(*) AS total FROM review_mester ; ";
		MyPage<ReviewMester> page = new MyPage<ReviewMester>();
		int totalElements = -1, pageNr = 1, pageSize = 10;

		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				totalElements = resultSet.getInt("total");
				System.out.println(totalElements);
			}
			ps.close();
			page.setTotalRezults(totalElements);

			if (searchCriteria.getPageNumber() != null) {
				pageNr = searchCriteria.getPageNumber();
			}
			if (searchCriteria.getPageSize() != null) {
				pageSize = searchCriteria.getPageSize();
			}

			sql2 = sql2 + (pageSize * (pageNr - 1)) + " , " + pageSize + " ;";
			page.setPageNumber(pageNr);
			page.setPageSize(pageSize);
			
			
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			System.out.println(sql2);
			ResultSet resultSet2 = ps2.executeQuery();
			while (resultSet2.next()) {
				reviews.add(getReviewFromDB(resultSet2));
			}
			page.setContentPage(reviews);

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
		return page;
	}

	private ReviewMester getReviewFromDB(ResultSet resultSet) throws SQLException {

		ReviewMester review = new ReviewMester();
		review.setId(resultSet.getInt("id"));
		review.setIdMester(resultSet.getInt("id_mester"));
		review.setIdClient(resultSet.getInt("id_client"));
		review.setRating(resultSet.getInt("rating"));
		review.setPrice(resultSet.getString("price"));
		review.setFeedback(resultSet.getString("feedback"));
		return review;
	}

	public ReviewMester setReviewMesterIntoDB(ReviewMester reviewMester, PreparedStatement ps) throws SQLException {

		ps.setInt(1, reviewMester.getIdMester());
		ps.setInt(2, reviewMester.getIdClient());
		ps.setInt(3, reviewMester.getRating());
		ps.setString(4, reviewMester.getPrice());
		ps.setString(5, reviewMester.getFeedback());
		return reviewMester;
	}

	

}
