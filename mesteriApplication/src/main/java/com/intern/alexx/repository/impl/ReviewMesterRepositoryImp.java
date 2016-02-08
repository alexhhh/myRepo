package com.intern.alexx.repository.impl;

 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.Client;
import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.ReviewMester;
import com.intern.alexx.repository.ReviewMesterRepository;

@Component
public class ReviewMesterRepositoryImp implements ReviewMesterRepository {

	
	@Autowired
	private DataSource dataSource;
	
	
	public void insert(Client client, Mester mester, ReviewMester reviewMester) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO review_mester (id_mester, id_client, rating, price, feedback) " + "VALUES (?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, client.getId() );
			ps.setInt(2, mester.getId());
			ps.setInt(3, reviewMester.getRating());
			ps.setString(4, reviewMester.getPrice() );
			ps.setString(5, reviewMester.getFeedback());
			 
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

	
	public void update(Client client, Mester mester, ReviewMester reviewMester) {
		String sql = "UPDATE review_mester (id_mester, id_client, rating, price, feedback) " + "VALUES (?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, client.getId() );
			ps.setInt(2, mester.getId());
			ps.setInt(3, reviewMester.getRating());
			ps.setString(4, reviewMester.getPrice() );
			ps.setString(5, reviewMester.getFeedback());
			 
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
		Connection conn = null;
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

	
	public ReviewMester getBy(MesterSearchCriteria searchCriteria) {
		// TODO  by id ,by mester , by client
		return null;
	}

}
