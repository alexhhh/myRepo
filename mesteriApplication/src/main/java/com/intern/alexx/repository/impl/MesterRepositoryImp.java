/**
 * 
 */
package com.intern.alexx.repository.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.repository.MesterRepository;


@Component
public class MesterRepositoryImp implements MesterRepository {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private GenerateSql generateSql;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(Mester mester) {
		String sql = "INSERT INTO MESTER (FIRST_NAME, LAST_NAME, DESCRIPTION, LOCATION) " + "VALUES (?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, mester.getFirstName());
			ps.setString(2, mester.getLastName());
			ps.setString(3, mester.getDescription());
			ps.setString(4, mester.getLocation());
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

	public void update(Mester mester) {
		
		String sql = "UPDATE  MESTER FIRST_NAME= ?, LAST_NAME= ?, DESCRIPTION= ?, LOCATION= ?  WHERE id = ?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			// review

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(5, mester.getId());
			ps.setString(1, mester.getFirstName());
			ps.setString(2, mester.getLastName());
			ps.setString(3, mester.getDescription());
			ps.setString(4, mester.getLocation());
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

	public void delete(Mester mester) {
		String sql = "DELETE FROM MESTER  WHERE id = ? ";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mester.getId());
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

	public Mester getById(Mester mester) {
		Mester newMester = null;
		String sql = "SELECT * FROM mester WHERE id = ?";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mester.getId());
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				newMester = new Mester();
				newMester.setId(resultSet.getInt("id"));
				newMester.setFirstName(resultSet.getString("first_name"));
				newMester.setLastName(resultSet.getString("last_name"));
				newMester.setLocation(resultSet.getString("location"));
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

		return mester;
	}

	

	 
	public MyPage search(MesterSearchCriteria searchCriteria) {

		String sql1 = generateSql.createQueryForCountElements(searchCriteria);
		String sql2 = generateSql.createQueryForElements(searchCriteria);
		MyPage page = null;
		Mester mester=null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
		
			int nrParm=1;
				if (searchCriteria.getFirstName() != null) {
					 ps.setString(nrParm, searchCriteria.getFirstName());
					 ps2.setString(nrParm, searchCriteria.getFirstName());
					 nrParm++;
					 }
				if (searchCriteria.getLastName() != null) {
					 ps.setString(nrParm, searchCriteria.getLastName());
					 ps2.setString(nrParm, searchCriteria.getLastName());
					 nrParm++;
					 }
				if (searchCriteria.getLocation() != null) {
					 ps.setString(nrParm, searchCriteria.getLocation());
					 ps2.setString(nrParm, searchCriteria.getLocation());
					 nrParm++;
					 }
				if (searchCriteria.getSpecialityName() != null) {
					 ps.setString(nrParm, searchCriteria.getSpecialityName());
					 ps2.setString(nrParm, searchCriteria.getSpecialityName());
					 nrParm++;
					 }
				if (searchCriteria.getEmail() != null) {
					 ps.setString(nrParm, searchCriteria.getEmail());
					 ps2.setString(nrParm, searchCriteria.getEmail());
					 nrParm++;
					 }
				if (searchCriteria.getPhoneNumber() != null) {
					 ps.setString(nrParm, searchCriteria.getPhoneNumber());
					 ps2.setString(nrParm, searchCriteria.getPhoneNumber());
					 nrParm++;
					 }
				if (searchCriteria.getRating() != null) {
					 ps.setInt(nrParm, searchCriteria.getRating());
					 ps2.setInt(nrParm, searchCriteria.getRating());
					 nrParm++;
					  }
				if (searchCriteria.getPrice() != null) {
					 ps.setString(nrParm, searchCriteria.getPrice());
					 ps2.setString(nrParm, searchCriteria.getPrice());
					 nrParm++;
					 
				}
				
				if(searchCriteria.getPageNumber() != null) {
				page.setPageNumber(searchCriteria.getPageNumber());
				}
				if(searchCriteria.getPageSize() != null) {
				page.setPageSize(searchCriteria.getPageSize());
				}
			ResultSet resultSet = ps.executeQuery();
		
			page.setTotalRezults(resultSet.getInt(1));
			ps.close();
			
			ResultSet resultSet2 = ps2.executeQuery();
			if (resultSet2.next()) {
				mester = new Mester();
				mester.setId(resultSet2.getInt("id"));
				mester.setFirstName(resultSet2.getString("first_name"));
				mester.setLastName(resultSet2.getString("last_name"));
				mester.setDescription(resultSet2.getString("description"));
				mester.setLocation(resultSet2.getString("location"));
				page.getContentPage().add(mester);
			}
			page.getContentPage();
			ps2.close();
		} catch (

		SQLException e)

		{
			throw new RuntimeException(e);
		} finally

		{
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}

			}
		}
		return page;
	}

}
