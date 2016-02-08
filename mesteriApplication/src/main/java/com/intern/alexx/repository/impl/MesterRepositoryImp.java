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
		// TODO Areview
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

	 
	public List<Mester> getByLocation(Mester mester) {

		Mester newMester = null;
		String sql = "SELECT * FROM mester WHERE location = ?";
		Connection conn = null;
		List<Mester> mesteri = new ArrayList<Mester>();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mester.getLocation());

			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				newMester = new Mester();
				newMester.setId(resultSet.getInt("id"));
				newMester.setFirstName(resultSet.getString("first_name"));
				newMester.setLastName(resultSet.getString("last_name"));
				newMester.setDescription(resultSet.getString("description"));
				newMester.setLocation(resultSet.getString("location"));
				mesteri.add(newMester);
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

		return mesteri;
	}

	public List<Mester> findAll() {
		String sql = "SELECT * FROM mester";
		Mester mester = null;
		List<Mester> mesteri = new ArrayList<Mester>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				mester = new Mester();
				mester.setId(resultSet.getInt("id"));
				mester.setFirstName(resultSet.getString("first_name"));
				mester.setLastName(resultSet.getString("last_name"));
				mester.setDescription(resultSet.getString("description"));
				mester.setLocation(resultSet.getString("location"));
				mesteri.add(mester);
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
		return mesteri;
	}

	 
	public List<Mester> search(MesterSearchCriteria searchCriteria) {

		String sql = generateSql.createString(searchCriteria);
		Mester mester = null;
		List<Mester> mesteri = new ArrayList<Mester>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

		
			int nrParm=1;
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
				 if (searchCriteria.getFirstName() != null) {
					 ps.setString(nrParm, searchCriteria.getFirstName());	 
				 }
				 
 
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				mester = new Mester();
				mester.setId(resultSet.getInt("id"));
				mester.setFirstName(resultSet.getString("first_name"));
				mester.setLastName(resultSet.getString("last_name"));
				mester.setDescription(resultSet.getString("description"));
				mester.setLocation(resultSet.getString("location"));
				mesteri.add(mester);
			}
			ps.close();
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
		return mesteri;
	}

}
