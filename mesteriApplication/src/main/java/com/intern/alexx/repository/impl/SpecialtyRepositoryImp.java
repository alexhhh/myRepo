package com.intern.alexx.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.Speciality;
import com.intern.alexx.repository.SpecialityRepository;

@Component
public class SpecialtyRepositoryImp implements SpecialityRepository {

	@Autowired
	private RepositoryConnectionUtil connectionUtil;

	@Override
	public void insert(Speciality speciality) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO speciality NUME_SPECIALITY " + "VALUES ?";

		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			addContactIntoDB(speciality, ps);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at insert contact  ", e);
		} finally {
			try {
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close insert contact  ", e);
			}
		}
	}

	@Override
	public void update(Speciality speciality) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE  speciality SET  NUME_SPECIALTY=?  WHERE id= ?";

		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			ps.setInt(2, speciality.getId());
			addContactIntoDB(speciality, ps);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at delete speciality  ", e);
		} finally {
			try {
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close delete speciality  ", e);
			}
		}
	}

	@Override
	public void delete(Speciality speciality) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM speciality  WHERE id_mester = ? ";

		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			ps.setInt(1, speciality.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("SQL Exception at delete speciality  ", e);
		} finally {
			try {
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close delete speciality  ", e);
			}
		}

	}
	
	@Override
	public Speciality getById(Speciality speciality) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM speciality WHERE id = ?";

		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			ps.setInt(1, speciality.getId());
			resultSet = ps.executeQuery();

			if (resultSet.next()) {
				getSpecialtyFromDB(resultSet);
			}

		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at get speciality by Id  ", e);
		} finally {
			try {
				connectionUtil.closeable(resultSet);
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close get specialty ", e);
			}
		}
		return speciality;
	}

	@Override
	public List<Speciality> getAllSpecialties() {
		List<Speciality> specialities = new ArrayList<Speciality>();
		String sql = " SELECT specialty.specialty_name FROM specialty";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				specialities.add(getSpecialtyFromDB(resultSet));
			}

		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at get all specialties  " , e);
		} finally {
			try {
				connectionUtil.closeable(resultSet);
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {		 
				throw new RepositoryException("SQLException at close get all specialties " , e);
			}
		}
		return specialities;
	}

	
	private void addContactIntoDB(Speciality speciality, PreparedStatement ps) throws SQLException {
		ps.setInt(1, speciality.getId());
		ps.setString(2, speciality.getSpecialityName());
	}

	private Speciality getSpecialtyFromDB(ResultSet resultSet) throws SQLException {
		Speciality speciality = new Speciality();
		speciality.setId(resultSet.getInt("id"));
		speciality.setSpecialityName(resultSet.getString("name_specialty"));
		return speciality;
	}


}
