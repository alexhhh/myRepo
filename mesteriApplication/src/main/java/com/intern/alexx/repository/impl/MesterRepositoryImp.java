package com.intern.alexx.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.intern.alexx.model.Contact;
import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.Speciality;
import com.intern.alexx.repository.ContactRepository;
import com.intern.alexx.repository.MesterRepository;
import com.intern.alexx.repository.SpecialityRepository;
import com.mysql.jdbc.Statement;

@Component
public class MesterRepositoryImp implements MesterRepository {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private RepositoryConnectionUtil connectionUtil;

	@Autowired
	private GenerateSql generateSql;

	@Autowired
	private ContactRepository contactRepo;

	@Autowired
	private SpecialityRepository specRepo;

	private static final Logger logger = LoggerFactory.getLogger(MesterRepositoryImp.class);

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(Mester mester) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet keys = null;
		String sql = "INSERT INTO MESTER (FIRST_NAME, LAST_NAME, DESCRIPTION, LOCATION) " + "VALUES (?,?,?,?)";
		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			addMesterIntoDB(mester, ps);
			ps.executeUpdate();
			logger.info("-+++ get mester:" + mester.toString() + "  +++-");
			keys = ps.getGeneratedKeys();
			keys.next();
			Integer key = keys.getInt(1);

			logger.info("- " + key + " -");

		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at insert mester  ", e);
		} finally {
			try {
				connectionUtil.closeable(keys);
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close insert mester  ", e);
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
			throw new RepositoryException("SQL Exception at update mester  ", e);
		} finally {
			try {
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close update mester  ", e);
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
			throw new RuntimeException("SQL Exception at delete mester  ", e);
		} finally {
			try {
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close delete mester  ", e);
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
			throw new RepositoryException("SQL Exception at get mester by Id  ", e);
		} finally {
			try {
				connectionUtil.closeable(resultSet);
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close getMesterByID  ", e);
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

	public void insertFullMester(Mester mester) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null, rs2 = null;
		Contact contact=mester.getContact();
		Speciality speciality=mester.getSpeciality();
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);

			Integer mesterKey = transactionalInsertMester(mester, conn, ps, rs);
			logger.info("@INSERT +++ get mester:" + mester.toString() + "  +++");
			logger.info("@INSERT+++ get mester key :" + mesterKey + " +++");
			contactRepo.transactionalInsertContract(mesterKey, contact, conn, ps);
			logger.info("@INSERT+++ get contact " + contact.toString() + "  +++");
			Integer specKey = specRepo.transactionalGetSpecialityID(speciality, conn, ps, rs2);
			logger.info("@INSERT+++ insert spec key: " + specKey + " +++");
			transactionalInsertIntoMesterHasSpeciality(specKey, specKey, conn, ps);

			conn.commit();
			logger.info("@INSERT+++ o trectu de commit +++");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RepositoryException("Error at connection rollback  ", e1);
			}
			throw new RepositoryException("SQL Exception at insert full mester  ", e);
		} finally {
			try {
				connectionUtil.closeable(rs);
				connectionUtil.closeable(rs2);
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close insert full mester  ", e);
			}
		}
	}

	public void updateFullMester(Mester mester) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null, rs2 = null;
		Contact contact=mester.getContact();
		Speciality speciality=mester.getSpeciality();
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			Integer mesterKey = mester.getId();

			transactionalDeleteFromMesterHasSpeciality(mesterKey, conn, ps);
			transactionalUpdateMester(mester, conn, ps);
			logger.info("@UPDATE+++ get mester:" + mester.toString() + "  +++");
			contactRepo.transactionalUpdateContract(mesterKey, contact, conn, ps);
			logger.info("@UPDATE+++ get contact " + contact.toString() + "  +++");
			Integer specKey = specRepo.transactionalGetSpecialityID(speciality, conn, ps, rs2);
			transactionalInsertIntoMesterHasSpeciality(mesterKey, specKey, conn, ps);

			conn.commit();
			logger.info("@UPDATE+++ o trectu de commit +++");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RepositoryException("Error at connection rollback  ", e1);
			}
			throw new RepositoryException("SQL Exception at update full mester  ", e);
		} finally {
			try {
				connectionUtil.closeable(rs);
				connectionUtil.closeable(rs2);
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close update full mester  ", e);
			}
		}
	}

	public void deleteFullMester(Mester mester) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			Integer mesterKey = mester.getId();

			transactionalDeleteFromMesterHasSpeciality(mesterKey, conn, ps);
			transactionalDeleteMester(mesterKey, conn, ps);
			contactRepo.transactionalDeleteContract(mesterKey, conn, ps);

			conn.commit();
			logger.info("@DELETE+++ o trectu de commit +++");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RepositoryException("Error at connection rollback  ", e1);
			}
			throw new RepositoryException("SQL Exception at delete full mester  ", e);
		} finally {
			try {
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close delete full mester  ", e);
			}
		}
	}

	private Integer transactionalInsertMester(Mester mester, Connection conn, PreparedStatement ps, ResultSet rs)
			throws SQLException {
		String sql = "INSERT INTO MESTER (FIRST_NAME, LAST_NAME, DESCRIPTION, LOCATION)  VALUES (?,?,?,?)";
		ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		addMesterIntoDB(mester, ps);
		ps.executeUpdate();
		rs = ps.getGeneratedKeys();
		rs.next();
		Integer mesterKey = rs.getInt(1);
		return mesterKey;
	}

	private void transactionalUpdateMester(Mester mester, Connection conn, PreparedStatement ps) throws SQLException {
		String sql = "UPDATE  mester SET FIRST_NAME= ?, LAST_NAME= ?, DESCRIPTION= ?, LOCATION= ?  WHERE id = ?";
		ps = conn.prepareStatement(sql);
		addMesterIntoDB(mester, ps);
		ps.setInt(5, mester.getId());
		ps.executeUpdate();
	}

	private void transactionalDeleteMester(Integer mesterKey, Connection conn, PreparedStatement ps)
			throws SQLException {
		String sql = "DELETE FROM MESTER  WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, mesterKey);
		ps.executeUpdate();
	}

	private void transactionalInsertIntoMesterHasSpeciality(Integer mesterKey, Integer specKey, Connection conn,
			PreparedStatement ps) throws SQLException {
		String sql = "INSERT INTO mester_has_speciality  (id_mester, id_speciality) VALUES (?,?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, mesterKey);
		ps.setInt(2, specKey);
		ps.executeUpdate();
	}

	private void transactionalDeleteFromMesterHasSpeciality(Integer mesterKey, Connection conn, PreparedStatement ps)
			throws SQLException {
		String sql = "DELETE FROM mester_has_speciality  WHERE id_mester = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, mesterKey);
		ps.executeUpdate();
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
			throw new RepositoryException("SQL Exception at count mesteri  ", e);
		} finally {
			try {
				connectionUtil.closeable(resultSet);
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close execute count statement ", e);
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
			throw new RepositoryException("SQL Exception at get search mesteri  ", e);
		} finally {
			try {
				connectionUtil.closeable(resultSet);
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close exec select statement ", e);
			}
		}
		return mesteri;
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
