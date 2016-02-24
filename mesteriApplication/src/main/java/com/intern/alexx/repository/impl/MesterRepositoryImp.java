package com.intern.alexx.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.repository.MesterRepository;

@Component
public class MesterRepositoryImp implements MesterRepository {

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private GenerateSql generateSql;

	public void setJdbcTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public void insert(Mester mester) {
		String sql = "INSERT INTO MESTER (ID, FIRST_NAME, LAST_NAME, DESCRIPTION, LOCATION) " + "VALUES (?,?,?,?,?)";
		template.update(sql, new Object[] { mester.getId(), mester.getFirstName(), mester.getLastName(),
				mester.getDescription(), mester.getLocation() });
	}

	public void update(Mester mester) {
		String sql = "UPDATE  mester SET FIRST_NAME= ?, LAST_NAME= ?, DESCRIPTION= ?, LOCATION= ?  WHERE id = ?";
		template.update(sql, new Object[] { mester.getFirstName(), mester.getLastName(), mester.getDescription(),
				mester.getLocation(), mester.getId() });
	}

	public void delete(String mesterId) {
		String sql = "DELETE FROM MESTER  WHERE id = ? ";
		template.update(sql, mesterId);
	}

	public Mester getById(String mesterId) {
		Mester mester = new Mester();
		String sql = "SELECT * FROM mester WHERE id = ?";
		template.query(sql, new Object[] { mesterId }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				getMesterFromDB(mester, rs);
			}
		});
		return mester;
	}

	public MyPage<Mester> prepareSearchForMester(MesterSearchCriteria searchCriteria) throws SQLException {
		MyPage<Mester> page = new MyPage<Mester>();
		page.setPageNumber(setThisPageNumber(searchCriteria));
		page.setPageSize(setThisPageSize(searchCriteria));

		page.setTotalRezults(executeSqlCountStatement(searchCriteria));
		System.out.println("----xxx---" + page.getTotalRezults().toString());
		page.setContentPage(executeSqlSelectStatement(searchCriteria));
		System.out.println("----xxx---" + page.getContentPage().toString());
		return page;
	}

	private int executeSqlCountStatement(MesterSearchCriteria searchCriteria) throws SQLException {
		String sql = generateSql.createQueryForCountElements(searchCriteria);
		System.out.println("----xxx---" + sql);
		verifyParam(searchCriteria);
		Integer totalMesteri = template.queryForObject(sql, Integer.class,
				new Object[] { searchCriteria.getLocation() });
		return totalMesteri;
	}

	private List<Mester> executeSqlSelectStatement(MesterSearchCriteria searchCriteria) throws SQLException {
		String sql = generateSql.createQueryForElements(searchCriteria);
		RowMapper<Mester> rm = BeanPropertyRowMapper.newInstance(Mester.class);
		List<Mester> mesteri = template.query(sql, new Object[] { searchCriteria.getLocation() }, rm);

		return mesteri;
	}

	private Mester getMesterFromDB(Mester mester, ResultSet resultSet) throws SQLException {
		mester.setId(resultSet.getString("id"));
		mester.setFirstName(resultSet.getString("first_name"));
		mester.setLastName(resultSet.getString("last_name"));
		mester.setDescription(resultSet.getString("description"));
		mester.setLocation(resultSet.getString("location"));
		return mester;
	}

	private int setThisPageNumber(MesterSearchCriteria searchCriteria) {
		int pageNumeber;
		if (searchCriteria.getPageNumber() != null) {
			pageNumeber = searchCriteria.getPageNumber();
		} else {
			pageNumeber = 1;
		}
		return pageNumeber;
	}

	private int setThisPageSize(MesterSearchCriteria searchCriteria) {
		int pageSize;
		if (searchCriteria.getPageSize() != null) {
			pageSize = searchCriteria.getPageSize();
		} else {
			pageSize = 10;
		}
		return pageSize;
	}

	private void verifyParam(MesterSearchCriteria searchCriteria) throws SQLException {

		if (searchCriteria.getFirstName() != null) {
			searchCriteria.getFirstName();

		}
		if (searchCriteria.getLastName() != null) {
			searchCriteria.getLastName();

		}
		if (searchCriteria.getLocation() != null) {
			searchCriteria.getLocation();

		}
		if (searchCriteria.getSpecialityName() != null) {
			searchCriteria.getSpecialityName();

		}
		if (searchCriteria.getEmail() != null) {
			searchCriteria.getEmail();

		}
		if (searchCriteria.getPhoneNumber() != null) {
			searchCriteria.getPhoneNumber();

		}
		if (searchCriteria.getRating() != null) {
			searchCriteria.getRating();

		}
		if (searchCriteria.getPrice() != null) {
			searchCriteria.getPrice();

		}

	}

	public void insertIntoMesterHasSpeciality(String mesterId, String specialityId) {
	 String sql = "INSERT INTO mester_has_speciality (id_mester, id_speciality) VALUES (?,?)";
	 template.update(sql, mesterId, specialityId);
	 }

	public void deleteFromMesterHasSpeciality(String mesterId )   {
		String sql = "DELETE FROM mester_has_speciality WHERE id_mester = ?";
		template.update(sql, mesterId);
	}


	// public void insertFullMester(Mester mester) {
	// Connection conn = null;
	// PreparedStatement ps = null;
	// ResultSet rs = null, rs2 = null;
	// Contact contact = mester.getContact();
	// Speciality speciality = mester.getSpeciality();
	// try {
	// conn = dataSource.getConnection();
	// conn.setAutoCommit(false);
	//
	// String mesterKey = transactionalInsertMester(mester, conn, ps, rs);
	// logger.info("@INSERT +++ get mester:" + mester.toString() + " +++");
	// logger.info("@INSERT+++ get mester key :" + mesterKey + " +++");
	// contactRepo.transactionalInsertContract(mesterKey, contact, conn, ps);
	// logger.info("@INSERT+++ get contact " + contact.toString() + " +++");
	// String specKey = specRepo.transactionalGetSpecialityID(speciality, conn,
	// ps, rs2);
	// logger.info("@INSERT+++ insert spec key: " + specKey + " +++");
	// transactionalInsertIntoMesterHasSpeciality(specKey, specKey, conn, ps);
	//
	// conn.commit();
	// logger.info("@INSERT+++ o trectu de commit +++");
	// } catch (SQLException e) {
	// try {
	// conn.rollback();
	// } catch (SQLException e1) {
	// throw new RepositoryException("Error at connection rollback ", e1);
	// }
	// throw new RepositoryException("SQL Exception at insert full mester ", e);
	// } finally {
	// try {
	// connectionUtil.closeable(rs);
	// connectionUtil.closeable(rs2);
	// connectionUtil.closeable(ps);
	// connectionUtil.closeable(conn);
	// } catch (Exception e) {
	// throw new RepositoryException("SQLException at close insert full mester
	// ", e);
	// }
	// }
	// }
	//
	// public void updateFullMester(Mester mester) {
	// Connection conn = null;
	// PreparedStatement ps = null;
	// ResultSet rs = null, rs2 = null;
	// Contact contact = mester.getContact();
	// Speciality speciality = mester.getSpeciality();
	// try {
	// conn = dataSource.getConnection();
	// conn.setAutoCommit(false);
	// String mesterKey = mester.getId();
	//
	// transactionalDeleteFromMesterHasSpeciality(mesterKey, conn, ps);
	// transactionalUpdateMester(mester, conn, ps);
	// logger.info("@UPDATE+++ get mester:" + mester.toString() + " +++");
	// contactRepo.transactionalUpdateContract(mesterKey, contact, conn, ps);
	// logger.info("@UPDATE+++ get contact " + contact.toString() + " +++");
	// String specKey = specRepo.transactionalGetSpecialityID(speciality, conn,
	// ps, rs2);
	// transactionalInsertIntoMesterHasSpeciality(mesterKey, specKey, conn, ps);
	//
	// conn.commit();
	// logger.info("@UPDATE+++ o trectu de commit +++");
	// } catch (SQLException e) {
	// try {
	// conn.rollback();
	// } catch (SQLException e1) {
	// throw new RepositoryException("Error at connection rollback ", e1);
	// }
	// throw new RepositoryException("SQL Exception at update full mester ", e);
	// } finally {
	// try {
	// connectionUtil.closeable(rs);
	// connectionUtil.closeable(rs2);
	// connectionUtil.closeable(ps);
	// connectionUtil.closeable(conn);
	// } catch (Exception e) {
	// throw new RepositoryException("SQLException at close update full mester
	// ", e);
	// }
	// }
	// }
	//
	// public void deleteFullMester(String mesterId) {
	// Connection conn = null;
	// PreparedStatement ps = null;
	//
	// try {
	// conn = dataSource.getConnection();
	// conn.setAutoCommit(false);
	// String mesterKey = mesterId;
	//
	// transactionalDeleteFromMesterHasSpeciality(mesterKey, conn, ps);
	// transactionalDeleteMester(mesterKey, conn, ps);
	// contactRepo.transactionalDeleteContract(mesterKey, conn, ps);
	//
	// conn.commit();
	// logger.info("@DELETE+++ o trectu de commit +++");
	// } catch (SQLException e) {
	// try {
	// conn.rollback();
	// } catch (SQLException e1) {
	// throw new RepositoryException("Error at connection rollback ", e1);
	// }
	// throw new RepositoryException("SQL Exception at delete full mester ", e);
	// } finally {
	// try {
	// connectionUtil.closeable(ps);
	// connectionUtil.closeable(conn);
	// } catch (Exception e) {
	// throw new RepositoryException("SQLException at close delete full mester
	// ", e);
	// }
	// }
	// }
	//
	// private String transactionalInsertMester(Mester mester, Connection conn,
	// PreparedStatement ps, ResultSet rs)
	// throws SQLException {
	// String sql = "INSERT INTO MESTER (FIRST_NAME, LAST_NAME, DESCRIPTION,
	// LOCATION) VALUES (?,?,?,?)";
	// ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	// addMesterIntoDB(mester, ps);
	// ps.executeUpdate();
	// rs = ps.getGeneratedKeys();
	// rs.next();
	// String mesterKey = rs.getString(1);
	// return mesterKey;
	// }
	//
	// private void transactionalUpdateMester(Mester mester, Connection conn,
	// PreparedStatement ps) throws SQLException {
	// String sql = "UPDATE mester SET FIRST_NAME= ?, LAST_NAME= ?, DESCRIPTION=
	// ?, LOCATION= ? WHERE id = ?";
	// ps = conn.prepareStatement(sql);
	// addMesterIntoDB(mester, ps);
	// ps.setString(5, mester.getId());
	// ps.executeUpdate();
	// }
	//
	// private void transactionalDeleteMester(String mesterKey, Connection conn,
	// PreparedStatement ps)
	// throws SQLException {
	// String sql = "DELETE FROM MESTER WHERE id = ?";
	// ps = conn.prepareStatement(sql);
	// ps.setString(1, mesterKey);
	// ps.executeUpdate();
	// }
	//

}
