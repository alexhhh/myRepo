package com.intern.alexx.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;
import com.intern.alexx.repository.ReviewMesterRepository;

@Component
public class ReviewMesterRepositoryImp implements ReviewMesterRepository {

	@Autowired
	private RepositoryConnectionUtil connectionUtil;

	public void insert(ReviewMester reviewMester) {

		String sql = "INSERT INTO review_mester (id_mester, id_client, rating, price, feedback) "
				+ "VALUES (?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			setReviewMesterIntoDB(reviewMester, ps);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at insert review  ", e);
		} finally {
			try {
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close insert review  ", e);
			}
		}
	}

	public void update(ReviewMester reviewMester) {
		String sql = "UPDATE review_mester SET id_mester=?, id_client=?, rating=?, price=?, feedback=? WHERE id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			setReviewMesterIntoDB(reviewMester, ps);
			ps.setInt(6, reviewMester.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at update review  ", e);
		} finally {
			try {
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close update review  ", e);
			}
		}
	}

	public void delete(ReviewMester reviewMester) {
		String sql = "DELETE FROM review_mester  WHERE id = ? ";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			ps.setInt(1, reviewMester.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at delete review  ", e);
		} finally {
			try {
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close delete review ", e);
			}
		}
	}

	public ReviewMester getById(ReviewMester reviewMester) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM review_mester WHERE id = ?";
		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			ps.setInt(1, reviewMester.getId());
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				getReviewFromDB(resultSet);
			}
			ps.close();
		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at get review by Id  ", e);
		} finally {
			try {
				connectionUtil.closeable(resultSet);
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close getByID  ", e);
			}
		}
		return reviewMester;
	}

	public MyPage<ReviewMester> getAllReviewMesterPage(MesterSearchCriteria searchCriteria) {
		Connection conn = null;
		PreparedStatement ps = null, ps2 = null;
		ResultSet resultSet = null, resultSet2 = null;
		String sql = "SELECT COUNT(*) AS total FROM review_mester ; ";
		MyPage<ReviewMester> page = new MyPage<ReviewMester>();

		if (searchCriteria.getPageNumber() != null) {
			page.setPageNumber(searchCriteria.getPageNumber());
		} else {
			page.setPageNumber(1);
		}
		if (searchCriteria.getPageSize() != null) {
			page.setPageSize(searchCriteria.getPageSize());
		} else {
			page.setPageSize(10);
		}

		String sql2 = "SELECT * FROM review_mester AS rm LIMIT " + (page.getPageSize() * (page.getPageNumber() - 1))
				+ " , " + page.getPageSize() + " ;";
		try {
			page.setTotalRezults(executeCountStatement(conn, ps, sql, resultSet));
			page.setContentPage(executeElementsStatement(conn, ps2, sql2, resultSet2));

		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at get all reviews  ", e);
		} finally {
			try {
				connectionUtil.closeable(resultSet);
				connectionUtil.closeable(ps);
				connectionUtil.closeable(resultSet2);
				connectionUtil.closeable(ps2);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close getAllReview   ", e);
			}
		}
		return page;
	}

	private int executeCountStatement(Connection conn, PreparedStatement ps, String sql, ResultSet resultSet)
			throws SQLException {
		int totalElements = -1;
		ps = connectionUtil.prepareConnection(conn, sql);
		resultSet = ps.executeQuery();
		if (resultSet.next()) {
			totalElements = resultSet.getInt("total");
		}
		return totalElements;
	}

	private List<ReviewMester> executeElementsStatement(Connection conn, PreparedStatement ps, String sql,
			ResultSet resultSet) throws SQLException {
		ps = connectionUtil.prepareConnection(conn, sql);
		List<ReviewMester> reviews = new ArrayList<ReviewMester>();
		resultSet = ps.executeQuery();
		while (resultSet.next()) {
			reviews.add(getReviewFromDB(resultSet));
		}
		return reviews;
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

	private ReviewMester setReviewMesterIntoDB(ReviewMester reviewMester, PreparedStatement ps) throws SQLException {

		ps.setInt(1, reviewMester.getIdMester());
		ps.setInt(2, reviewMester.getIdClient());
		ps.setInt(3, reviewMester.getRating());
		ps.setString(4, reviewMester.getPrice());
		ps.setString(5, reviewMester.getFeedback());
		return reviewMester;
	}

}
