package com.intern.alexx.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;
import com.intern.alexx.model.ReviewMester.Price;
import com.intern.alexx.repository.ReviewMesterRepository;

@Component
public class ReviewMesterRepositoryImp implements ReviewMesterRepository {

	@Autowired
	private JdbcTemplate template;

	public void setJdbcTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public void insert(ReviewMester reviewMester) {
		reviewMester.setId(GUIDGenerator.generatedID());
		String sql = "INSERT INTO review_mester (id, id_mester, id_client, rating, price, feedback) "
				+ "VALUES (?,?,?,?,?,?)";
		template.update(sql,
				new Object[] { reviewMester.getId(), reviewMester.getIdMester(), reviewMester.getIdClient(),
						reviewMester.getRating(), reviewMester.getPrice().getValue(), reviewMester.getFeedback() });
	}

	public void update(ReviewMester reviewMester) {
		String sql = "UPDATE review_mester SET id_mester=?, id_client=?, rating=?, price=?, feedback=? WHERE id=?";
		template.update(sql,
				new Object[] { reviewMester.getIdMester(), reviewMester.getIdClient(), reviewMester.getRating(),
						reviewMester.getPrice().getValue(), reviewMester.getFeedback(), reviewMester.getId() });
	}

	public void delete(String idReview) {
		String sql = "DELETE FROM review_mester  WHERE id = ? ";
		template.update(sql, idReview);
	}

	public ReviewMester getById(String idReview) {
		String sql = "SELECT * FROM review_mester WHERE id = ?";
		ReviewMester review = new ReviewMester();
		template.query(sql, new Object[] { idReview }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				getReviewFromDB(review, rs);
			}
		});
		return review;
	}

	public float getAvgRatingForMester(String idReview) {
		String sql = "SELECT AVG(rating) FROM review_mester WHERE id_mester = ?";
		float avgValue = template.queryForObject(sql, new Object[] { idReview }, Float.class);
		return avgValue;
	}

	
	
	public MyPage<ReviewMester> getAllReviewsPage(Integer pageSize, Integer pageNumber) throws SQLException {
		MyPage<ReviewMester> page = new MyPage<ReviewMester>();
		page.setPageNumber(setPageNumberParam(pageNumber));
		page.setPageSize(setPageSizeParam(pageSize));

		String sql = "SELECT * FROM review_mester AS rm LIMIT " + (page.getPageSize() * (page.getPageNumber() - 1))
				+ " , " + page.getPageSize() + " ;";

		page.setTotalRezults(executeCountStatementForAllReviews());
		page.setContentPage(executeElementsStatementForAllReviews(sql));
		return page;
	}

	private int executeCountStatementForAllReviews() throws SQLException {
		String sql = "SELECT COUNT(*) AS total FROM review_mester;";
		Integer totalElements = template.queryForObject(sql, Integer.class);
		return totalElements;
	}

	private List<ReviewMester> executeElementsStatementForAllReviews(String sql) throws SQLException {
		List<ReviewMester> reviews = new ArrayList<ReviewMester>();
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<String, Object> row : rows) {
			ReviewMester review = new ReviewMester();
			reviewRowMapper(review, row);
			reviews.add(review);
		}
		return reviews;
	}

	public MyPage<ReviewMester> getAllReviewForMester(String idMester, Integer pageSize, Integer pageNumber)
			throws SQLException {
		MyPage<ReviewMester> page = new MyPage<ReviewMester>();
		page.setPageNumber(setPageNumberParam(pageNumber));
		page.setPageSize(setPageSizeParam(pageSize));
		String sql = "SELECT * FROM review_mester AS rm  WHERE id_mester = ? LIMIT "
				+ (page.getPageSize() * (page.getPageNumber() - 1)) + " , " + page.getPageSize() + " ;";
		page.setTotalRezults(executeCountStatement(idMester));
		page.setContentPage(executeElementsStatement(idMester, sql));
		return page;
	}

	private int executeCountStatement(String idMester) throws SQLException {
		String sql = "SELECT COUNT(*) AS total FROM review_mester WHERE id_mester=? ;";
		Integer totalElements = template.queryForObject(sql, Integer.class, idMester);
		return totalElements;
	}

	private List<ReviewMester> executeElementsStatement(String idMester, String sql) throws SQLException {
		List<ReviewMester> reviews = new ArrayList<ReviewMester>();
		List<Map<String, Object>> rows = template.queryForList(sql, new Object[] { idMester });
		for (Map<String, Object> row : rows) {
			ReviewMester review = new ReviewMester();
			reviewRowMapper(review, row);
			reviews.add(review);
		}
		return reviews;
	}

	private Integer setPageSizeParam(Integer pageSize) {
		if (pageSize == null)
			pageSize = 10;
		return pageSize;
	}

	private Integer setPageNumberParam(Integer pageNumber) {
		if (pageNumber == null)
			pageNumber = 1;
		return pageNumber;
	}

	private ReviewMester reviewRowMapper(ReviewMester review, Map<String, Object> row) throws SQLException {
		review.setId((String) (row.get("id")));
		review.setIdMester((String) (row.get("id_mester")));
		review.setIdClient((String) (row.get("id_client")));
		review.setRating((int) (row.get("rating")));
		int value = ((int) (row.get("price")));
		setPriceEnum(review, value);
		review.setFeedback((String) (row.get("feedback")));
		return review;
	}

	private ReviewMester getReviewFromDB(ReviewMester review, ResultSet resultSet) throws SQLException {

		review.setId(resultSet.getString("id"));
		review.setIdMester(resultSet.getString("id_mester"));
		review.setIdClient(resultSet.getString("id_client"));
		review.setRating(resultSet.getInt("rating"));
		int value = resultSet.getInt("price");
		setPriceEnum(review, value);
		review.setFeedback(resultSet.getString("feedback"));
		return review;
	}

	private void setPriceEnum(ReviewMester review, int value) throws SQLException {
		if (value == 1) {
			review.setPrice(Price.LOW);
		} else if (value == 2) {
			review.setPrice(Price.MEDIUM);
		} else if (value == 3) {
			review.setPrice(Price.HIGH);
		} else
			throw new IllegalArgumentException("the price is invalide.");
	}

}
