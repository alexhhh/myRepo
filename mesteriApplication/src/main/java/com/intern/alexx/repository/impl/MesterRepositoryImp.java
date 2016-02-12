/**
 * 
 */
package com.intern.alexx.repository.impl;


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
	private RepositoryConnectionUtil connectionUtil;

	@Autowired
	private GenerateSql generateSql;

 

	public void insert(Mester mester) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO MESTER (FIRST_NAME, LAST_NAME, DESCRIPTION, LOCATION) " + "VALUES (?,?,?,?)";

		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			addMesterIntoDB(mester, ps);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at insert mester  " , e);
		} finally {
			try {
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {	 
				throw new RepositoryException("SQLException at close insert mester  " , e);
			}
		}
	}

	public void update(Mester mester) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE  mester SET FIRST_NAME= ?, LAST_NAME= ?, DESCRIPTION= ?, LOCATION= ?  WHERE id = ?";

		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			ps.setInt(5, mester.getId());
			addMesterIntoDB(mester, ps);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at update mester  " , e);
		} finally {
			try {
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close update mester  " , e);
			}
		}
	}

	public void delete(Mester mester) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM MESTER  WHERE id = ? ";

		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			ps.setInt(1, mester.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("SQL Exception at delete mester  " , e);
		} finally {
			try {
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close delete mester  " , e);
			}
		}
	}

	public Mester getById(Mester mester) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM mester WHERE id = ?";

		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			ps.setInt(1, mester.getId());
			resultSet = ps.executeQuery();

			if (resultSet.next()) {
				getMesterFromDB(resultSet);
			}

		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at get mester by Id  " , e);
		} finally {
			try {
				connectionUtil.closeable(resultSet);
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {		 
				throw new RepositoryException("SQLException at close getMesterByID  " , e);
			}
		}

		return mester;
	}

	public MyPage<Mester> setupTheSearchMesterPage(MesterSearchCriteria searchCriteria) {
		MyPage<Mester> page = new MyPage<Mester>();
		page.setTotalRezults(executeSqlCountStatement(searchCriteria));

		if (searchCriteria.getPageNumber() != null) {
			page.setPageNumber(searchCriteria.getPageNumber());
		} else {
			page.setPageNumber(1);
		}
		if (searchCriteria.getPageSize() != null) {
			page.setPageSize(searchCriteria.getPageSize());
		} else {
			page.setPageSize(20);
		}
		page.setContentPage(executeSqlSelectStatement(searchCriteria));
		return page;
	}

	private int executeSqlCountStatement(MesterSearchCriteria searchCriteria) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;

		String sql = generateSql.createQueryForCountElements(searchCriteria);
		int totalElements = 0;
		try {

			ps = connectionUtil.prepareConnection(conn, sql);
			verifyParam(ps, searchCriteria);
			resultSet = ps.executeQuery();

			if (resultSet.next()) {
				totalElements = resultSet.getInt("total");
			}

		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at count mesteri  " , e);
		} finally {
			try {
				connectionUtil.closeable(resultSet);
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {		 
				throw new RepositoryException("SQLException at close execute count statement " , e);
			}
		}

		return totalElements;
	}

	private List<Mester> executeSqlSelectStatement(MesterSearchCriteria searchCriteria) {
		String sql = generateSql.createQueryForElements(searchCriteria);
		List<Mester> mesteri = new ArrayList<Mester>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			verifyParam(ps, searchCriteria);
			resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				mesteri.add(getMesterFromDB(resultSet));
			}


		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at get search mesteri  " , e);
		} finally {
			try {
				connectionUtil.closeable(resultSet);
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {		 
				throw new RepositoryException("SQLException at close exec select statement " , e);
			}
		}
		return mesteri;
	}

	private Mester getMesterFromDB(ResultSet resultSet) throws SQLException {
		Mester mester = new Mester();
		mester.setId(resultSet.getInt("id"));
		mester.setFirstName(resultSet.getString("first_name"));
		mester.setLastName(resultSet.getString("last_name"));
		mester.setDescription(resultSet.getString("description"));
		mester.setLocation(resultSet.getString("location"));
		return mester;
	}

	private void addMesterIntoDB(Mester mester, PreparedStatement ps) throws SQLException {
		ps.setString(1, mester.getFirstName());
		ps.setString(2, mester.getLastName());
		ps.setString(3, mester.getDescription());
		ps.setString(4, mester.getLocation());
	}

	private void verifyParam(PreparedStatement ps, MesterSearchCriteria searchCriteria) throws SQLException {
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
	}

}
