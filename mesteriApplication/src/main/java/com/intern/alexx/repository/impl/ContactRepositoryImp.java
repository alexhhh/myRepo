package com.intern.alexx.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.Contact;
import com.intern.alexx.repository.ContactRepository;

@Component
public class ContactRepositoryImp implements ContactRepository {

	@Autowired
	private RepositoryConnectionUtil connectionUtil;

	public void insert(Contact contact) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO contact (ID_MESTER, NUMAR_TELEFON, EMAIL, SITE, SOCIAL_PLATFORM) "
				+ "VALUES (?,?,?,?,?)";

		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			addContactIntoDB(contact, ps);
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

	public void update(Contact contact) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE  contact SET  NUMAR_TELEFON= ?, EMAIL= ?, SITE= ?, SOCIAL_PLATFORM=?  WHERE id_mester = ?";

		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			ps.setInt(5, contact.getId());
			addContactIntoDB(contact, ps);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RepositoryException("SQL Exception at update contact  ", e);
		} finally {
			try {
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close update contact  ", e);
			}
		}

	}

	public void delete(Contact contact) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM contact  WHERE id_mester = ? ";

		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			ps.setInt(1, contact.getIdMester());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("SQL Exception at delete contact  ", e);
		} finally {
			try {
				connectionUtil.closeable(ps);
				connectionUtil.closeable(conn);
			} catch (Exception e) {
				throw new RepositoryException("SQLException at close delete contact  ", e);
			}
		}
	}

	public Contact getByIdMester(Contact contact) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM contact WHERE id_mester = ?";

		try {
			ps = connectionUtil.prepareConnection(conn, sql);
			ps.setInt(1, contact.getId());
			resultSet = ps.executeQuery();

			if (resultSet.next()) {
				getContactFromDB(resultSet);
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
		return contact;
	}

	public void addContactIntoDB(Contact contact, PreparedStatement ps) throws SQLException {
		ps.setInt(1, contact.getIdMester());
		ps.setString(2, contact.getTelNr());
		ps.setString(3, contact.getEmail());
		ps.setString(4, contact.getSite());
		ps.setString(5, contact.getSocialPlatform());
	}

	private Contact getContactFromDB(ResultSet resultSet) throws SQLException {

		Contact contact = new Contact();
		contact.setId(resultSet.getInt("id"));
		contact.setIdMester(resultSet.getInt("id_mester"));
		contact.setTelNr(resultSet.getString("numar_telefon"));
		contact.setEmail(resultSet.getString("email"));
		contact.setSite(resultSet.getString("site"));
		contact.setSocialPlatform(resultSet.getString("social_platform"));
		return contact;
	}

	public void transactionalInsertContract(Integer mesterKey, Contact contact, Connection conn, PreparedStatement ps)
			throws SQLException {
		String sql = " INSERT INTO contact (ID_MESTER, NUMAR_TELEFON, EMAIL, SITE, SOCIAL_PLATFORM) VALUES (?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		contact.setIdMester(mesterKey);
		addContactIntoDB(contact, ps);
		ps.executeUpdate();
	}

	public void transactionalUpdateContract(Integer mesterKey, Contact contact, Connection conn, PreparedStatement ps)
			throws SQLException {
		String sql = " UPDATE contact SET NUMAR_TELEFON=?, EMAIL=?, SITE=?, SOCIAL_PLATFORM=? WHERE id_mester=? ";
		ps = conn.prepareStatement(sql);
		ps.setInt(5, mesterKey);
		ps.setString(1, contact.getTelNr());
		ps.setString(2, contact.getEmail());
		ps.setString(3, contact.getSite());
		ps.setString(4, contact.getSocialPlatform());
		ps.executeUpdate();
	}

	public void transactionalDeleteContract(Integer mesterKey, Connection conn, PreparedStatement ps)
			throws SQLException {
		String sql = "DELETE FROM contact  WHERE id_mester = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, mesterKey);
		ps.executeUpdate();
	}

}
