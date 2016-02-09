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

	private Connection conn = null;
	private Integer pageNumber = null, pageSize = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(Mester mester) {
		conn=null;
		String sql = "INSERT INTO MESTER (FIRST_NAME, LAST_NAME, DESCRIPTION, LOCATION) " + "VALUES (?,?,?,?)";

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			addMesterIntoDB(mester, ps);
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
		conn=null;
		String sql = "UPDATE  MESTER FIRST_NAME= ?, LAST_NAME= ?, DESCRIPTION= ?, LOCATION= ?  WHERE id = ?";
		try {
			conn = dataSource.getConnection();
			// review

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(5, mester.getId());
			addMesterIntoDB(mester, ps);
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
		conn=null;
		String sql = "DELETE FROM MESTER  WHERE id = ? ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mester.getId());
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

	public Mester getById(Mester mester) {
		conn=null;
		String sql = "SELECT * FROM mester WHERE id = ?";

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mester.getId());
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				getMesterFromDB(resultSet);
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
		MyPage page = new MyPage();
		
		page.setTotalRezults(executeSqlCountStatement(sql1, searchCriteria));
		page.setPageNumber(pageNumber);
		page.setPageSize(pageSize);
		page.setContentPage(executeSqlSelectStatement(sql2, searchCriteria));
		
		return page;
	}

	public void verifyParam(PreparedStatement ps, MesterSearchCriteria searchCriteria) throws SQLException {
		int nrParm = 1;
		if (searchCriteria.getFirstName() != null) {
			ps.setString(nrParm, searchCriteria.getFirstName());
			nrParm++;
		}
		if (searchCriteria.getLastName() != null) {
			ps.setString(nrParm, searchCriteria.getLastName());
			nrParm++;
		}
		if (searchCriteria.getLocation() != null) {
			ps.setString(nrParm, searchCriteria.getLocation());
			nrParm++;
		}
		if (searchCriteria.getSpecialityName() != null) {
			ps.setString(nrParm, searchCriteria.getSpecialityName());
			nrParm++;
		}
		if (searchCriteria.getEmail() != null) {
			ps.setString(nrParm, searchCriteria.getEmail());
			nrParm++;
		}
		if (searchCriteria.getPhoneNumber() != null) {
			ps.setString(nrParm, searchCriteria.getPhoneNumber());
			nrParm++;
		}
		if (searchCriteria.getRating() != null) {
			ps.setInt(nrParm, searchCriteria.getRating());
			nrParm++;
		}
		if (searchCriteria.getPrice() != null) {
			ps.setString(nrParm, searchCriteria.getPrice());
			nrParm++;
		}

		if (searchCriteria.getPageNumber() != null) {
			pageNumber = searchCriteria.getPageNumber();
		}
		if (searchCriteria.getPageSize() != null) {
			pageSize = searchCriteria.getPageSize();
		}
	}

	public Mester getMesterFromDB(ResultSet resultSet) throws SQLException {

		Mester mester = new Mester();
		mester.setId(resultSet.getInt("id"));
		mester.setFirstName(resultSet.getString("first_name"));
		mester.setLastName(resultSet.getString("last_name"));
		mester.setDescription(resultSet.getString("description"));
		mester.setLocation(resultSet.getString("location"));
		return mester;
	}

	public void addMesterIntoDB(Mester mester, PreparedStatement ps) throws SQLException {

		ps.setString(1, mester.getFirstName());
		ps.setString(2, mester.getLastName());
		ps.setString(3, mester.getDescription());
		ps.setString(4, mester.getLocation());

	}

	public int executeSqlCountStatement(String sql, MesterSearchCriteria searchCriteria) {
		conn=null;
		int totalElements ;
		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(sql);
			verifyParam(ps, searchCriteria);
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

	public List<Mester> executeSqlSelectStatement(String sql, MesterSearchCriteria searchCriteria) {
		List<Mester> mesteri = new ArrayList<Mester>();
		conn=null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement ps2 = conn.prepareStatement(sql);
			System.out.println(sql);
			verifyParam(ps2, searchCriteria);
			ResultSet resultSet2 = ps2.executeQuery();
			if (resultSet2.next()) {
				mesteri.add(getMesterFromDB(resultSet2));
			}
			ps2.close();

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
		return mesteri;
	}

}
