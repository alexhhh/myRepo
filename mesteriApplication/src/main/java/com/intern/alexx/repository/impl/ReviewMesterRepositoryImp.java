package com.intern.alexx.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.FullReview;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;
import com.intern.alexx.repository.ReviewMesterRepository;

@Component
public class ReviewMesterRepositoryImp implements ReviewMesterRepository {

	@Autowired
	private JdbcTemplate template;

	public void setJdbcTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	private static Logger log = LoggerFactory.getLogger(ReviewMesterRepositoryImp.class);
	
	public void insert(ReviewMester reviewMester) {
		reviewMester.setId(GUIDGenerator.generatedID());
		String sql = "INSERT INTO review_mester (id, id_mester, id_client, title, rating, price, feedback) "
				+ "VALUES (?,?,?,?,?,?,?)";
		template.update(sql,
				new Object[] { reviewMester.getId(), reviewMester.getIdMester(), reviewMester.getIdClient(),
						reviewMester.getTitle(), reviewMester.getRating(), reviewMester.getPrice(),
						reviewMester.getFeedback() });
	}

	public void update(ReviewMester reviewMester) {
		String sql = "UPDATE review_mester SET id_mester=?, id_client=?,title=?, rating=?, price=?, feedback=? WHERE id=?";
		template.update(sql,
				new Object[] { reviewMester.getIdMester(), reviewMester.getIdClient(), reviewMester.getTitle(),
						reviewMester.getRating(), reviewMester.getPrice(), reviewMester.getFeedback(),
						reviewMester.getId() });
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

	public int getAvgRatingForMester(String idMester) {
		String sql = "SELECT ROUND(AVG(rating)) FROM review_mester WHERE id_mester = ?";
		return template.queryForObject(sql, new Object[] { idMester }, Integer.class);	 
	}

	public int getAvgPriceForMester(String idMester) {
		String sql = "SELECT ROUND(AVG(price)) FROM review_mester WHERE id_mester = ?";
		return template.queryForObject(sql, new Object[] { idMester }, Integer.class);		  
	}

	public MyPage<ReviewMester> getAllReviewsPage(Integer pageSize, Integer pageNumber) throws SQLException {		 
		String sql = "SELECT * FROM review_mester AS rm LIMIT "+ pageSize + " , " + pageNumber + " ;";		 
		return setMyPageForAll(sql, pageSize, pageNumber);
	}
 
	public MyPage<ReviewMester> getAllReviewFromClient(String idClient, Integer pageSize, Integer pageNumber )
			throws SQLException {	 
		String sql = "SELECT * FROM review_mester AS rm  WHERE id_client = ? LIMIT " + pageNumber + " , " + pageSize + " ;";		 
		return setMyPage(sql, pageSize, pageNumber, idClient);
	}

	public MyPage<ReviewMester> getAllReviewForMester(String idMester, Integer pageSize, Integer pageNumber )
			throws SQLException {	 
		String sql = "SELECT * FROM review_mester AS rm  WHERE id_mester = ? LIMIT " + pageNumber + " , " + pageSize + " ;"; 
		return setMyPage(sql, pageSize, pageNumber, idMester);
	}

	public MyPage<FullReview> getAllFullReviewsPage(Integer pageSize, Integer pageNumber) throws SQLException {
		MyPage<FullReview> page = new MyPage<FullReview>();
		page.setPageNumber(setPageNumberParam(pageNumber));
		page.setPageSize(setPageSizeParam(pageSize));
		String sql = "SELECT rm.* , m.first_name , m.last_name FROM review_mester AS rm  LEFT JOIN mester AS m ON rm.id_mester=m.id  LIMIT "
				+ pageNumber  + " , " + pageSize  + " ;";
		page.setTotalResults(executeCountStatementForAllReviews());
		page.setContentPage(executeElementsStatementForAllFullReviews(sql));
		return page;
	}

	public MyPage<FullReview> getAllFullReviewsFromClient(String idClient, Integer pageSize, Integer pageNumber)
			throws SQLException {
		MyPage<FullReview> page = new MyPage<FullReview>();
		page.setPageNumber(setPageNumberParam(pageNumber));
		page.setPageSize(setPageSizeParam(pageSize));
		String sql = "SELECT rm.* , m.first_name , m.last_name FROM review_mester AS rm LEFT JOIN mester As m ON rm.id_mester=m.id WHERE id_client = ? LIMIT "
				+ ((page.getPageNumber())) + " , " + page.getPageSize() + " ;";
		page.setTotalResults(executeCountClientStatement(idClient));
		page.setContentPage(executeElementsStatementForReview(idClient, sql));
		return page;
	}
	
	private MyPage<ReviewMester> setMyPage (String sql,Integer pageSize, Integer pageNumber, String id) throws SQLException{
		MyPage<ReviewMester> page = new MyPage<ReviewMester>();
		page.setPageNumber(setPageNumberParam(pageNumber));
		page.setPageSize(setPageSizeParam(pageSize));		 
		page.setTotalResults(executeCountStatement(id));
		page.setContentPage(executeElementsStatement(id, sql));		
		return page;
	}
	
	private MyPage<ReviewMester> setMyPageForAll (String sql,Integer pageSize, Integer pageNumber) throws SQLException{
		MyPage<ReviewMester> page = new MyPage<ReviewMester>();
		page.setPageNumber(setPageNumberParam(pageNumber));
		page.setPageSize(setPageSizeParam(pageSize));		 
		page.setTotalResults(executeCountStatementForAllReviews());
		page.setContentPage(executeElementsStatementForAllReviews(sql));		
		return page;
	}
	
	private List<FullReview> executeElementsStatementForAllFullReviews(String sql) throws SQLException {
		List<FullReview> reviews = new ArrayList<FullReview>();
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<String, Object> row : rows) { 
			reviews.add(fullReviewRowMapper(row));
		} return reviews;
	}
	
	private List<FullReview> executeElementsStatementForReview(String idMester, String sql) throws SQLException {
		List<FullReview> reviews = new ArrayList<FullReview>();
		List<Map<String, Object>> rows = template.queryForList(sql, new Object[] { idMester });
		for (Map<String, Object> row : rows) { 
			reviews.add(fullReviewRowMapper(row));
		} return reviews;
	}
 
	private int executeCountStatementForAllReviews() throws SQLException {
		String sql = "SELECT COUNT(*) AS total FROM review_mester;";
		Integer totalElements = template.queryForObject(sql, Integer.class);
		return totalElements;
	}

	private int executeCountClientStatement(String idClient) throws SQLException {
		String sql = "SELECT COUNT(*) AS total FROM review_mester WHERE id_client=? ;";
		Integer totalElements = template.queryForObject(sql, Integer.class, idClient);
		return totalElements;
	}

	private int executeCountStatement(String idMester) throws SQLException {
		String sql = "SELECT COUNT(*) AS total FROM review_mester WHERE id_mester=? ;";
		Integer totalElements = template.queryForObject(sql, Integer.class, idMester);
		return totalElements;
	}

	private List<ReviewMester> executeElementsStatementForAllReviews(String sql) throws SQLException {
		List<ReviewMester> reviews = new ArrayList<ReviewMester>();
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<String, Object> row : rows) {
			reviews.add(reviewRowMapper(row));
		} return reviews;
	}
	
	private List<ReviewMester> executeElementsStatement(String idMester, String sql) throws SQLException {
		List<ReviewMester> reviews = new ArrayList<ReviewMester>();
		List<Map<String, Object>> rows = template.queryForList(sql, new Object[] { idMester });
		for (Map<String, Object> row : rows) { 
			reviews.add(reviewRowMapper(row));
		} return reviews;
	}

	private FullReview fullReviewRowMapper( Map<String, Object> row) throws SQLException {
		FullReview review = new FullReview();
		review.setId((String) (row.get("id")));
		review.setIdMester((String) (row.get("id_mester")));
		review.setIdClient((String) (row.get("id_client")));
		review.setTitle((String) (row.get("title")));
		review.setRating((int) (row.get("rating")));
		review.setPrice((int) (row.get("price")));
		review.setFeedback((String) (row.get("feedback")));
		review.setFirstName((String) (row.get("first_name")));
		review.setLastName((String) (row.get("last_name")));
		log.info("-- test full review "+ review.toString() );
		return review;
	}

	private ReviewMester reviewRowMapper(Map<String, Object> row) throws SQLException {
		ReviewMester review = new ReviewMester();
		review.setId((String) (row.get("id")));
		review.setIdMester((String) (row.get("id_mester")));
		review.setIdClient((String) (row.get("id_client")));
		review.setTitle((String) (row.get("title")));
		review.setRating((int) (row.get("rating")));
		review.setPrice((int) (row.get("price")));
		review.setFeedback((String) (row.get("feedback")));
		return review;
	}

	private ReviewMester getReviewFromDB(ReviewMester review, ResultSet resultSet) throws SQLException {
		review.setId(resultSet.getString("id"));
		review.setIdMester(resultSet.getString("id_mester"));
		review.setIdClient(resultSet.getString("id_client"));
		review.setTitle(resultSet.getString("title"));
		review.setRating(resultSet.getInt("rating"));
		review.setPrice(resultSet.getInt("price"));
		review.setFeedback(resultSet.getString("feedback"));
		return review;
	}

	private Integer setPageSizeParam(Integer pageSize) {
		if (pageSize == null)
			pageSize = 10;
		return pageSize;
	}

	private Integer setPageNumberParam(Integer pageNumber) {
		if (pageNumber == null){
			pageNumber = 1;}
		return pageNumber;
	}

 
 
}
