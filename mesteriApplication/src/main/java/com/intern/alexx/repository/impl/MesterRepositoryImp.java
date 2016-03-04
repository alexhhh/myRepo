package com.intern.alexx.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.repository.MesterRepository;

@Component
public class MesterRepositoryImp implements MesterRepository {

	@Autowired
	private JdbcTemplate template;
	
	@Autowired
	private NamedParameterJdbcTemplate namendTemplate;
	
	@Autowired
	private GenerateSql generateSql;

	public void setJdbcTemplate(JdbcTemplate template) {
		this.template = template;
	}

	protected NamedParameterJdbcTemplate getNamedParamJdbcTemplate() {
		return getNamedParamJdbcTemplate() ;
	}
	
	public void insert(Mester mester) {
		mester.setId(GUIDGenerator.generatedID());
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
		page.setContentPage(executeSqlSelectStatement(searchCriteria));
		return page;
	}

	private int executeSqlCountStatement(MesterSearchCriteria searchCriteria) throws SQLException {
		String sql = generateSql.createQueryForCountElements(searchCriteria);
		System.out.println("--- xxx---" + sql);
		Map<String, String> paramMap = verifyParam(searchCriteria);
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		Integer totalMesteri = (Integer) namendTemplate.queryForObject(sql, paramSource, Integer.class);
		return totalMesteri;
	}

	 
	private List<Mester> executeSqlSelectStatement(MesterSearchCriteria searchCriteria)
			throws SQLException {
		String sql = generateSql.createQueryForElements(searchCriteria);
		System.out.println("--- xxx---" + sql);
		RowMapper<Mester> rm = BeanPropertyRowMapper.newInstance(Mester.class);
		Map<String, String> paramMap = verifyParam(searchCriteria);
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		List<Mester> mesteri= (List<Mester>) namendTemplate.query(sql, paramSource, rm);
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

	private Map<String, String> verifyParam(MesterSearchCriteria searchCriteria) throws SQLException {
		Map<String, String> paramMap = new HashMap<String, String>();
		if (searchCriteria.getFirstName() != null) {
			paramMap.put("first_name", searchCriteria.getFirstName());
		}
		if (searchCriteria.getLastName() != null) {
			paramMap.put("last_name", searchCriteria.getLastName());
		}
		if (searchCriteria.getLocation() != null) {
			paramMap.put("location", searchCriteria.getLocation());
		}
		if (searchCriteria.getSpecialityName() != null) {
			paramMap.put("speciality_name", searchCriteria.getSpecialityName());
		}
		if (searchCriteria.getEmail() != null) {
			paramMap.put("email", searchCriteria.getEmail());
		}
		if (searchCriteria.getPhoneNumber() != null) {
			paramMap.put("phoneNumber", searchCriteria.getPhoneNumber());
		}
		if (searchCriteria.getRating() != null) {
			paramMap.put("rating", searchCriteria.getRating().toString());
		}
		if (searchCriteria.getPrice() != null) {
			paramMap.put("price", searchCriteria.getPrice().name());
		}
		return paramMap;
	}

	public void insertIntoMesterHasSpeciality(String mesterId, String specialityId) {
		String sql = "INSERT INTO mester_has_speciality (id_mester, id_speciality) VALUES (?,?)";
		template.update(sql, mesterId, specialityId);
	}

	public void deleteFromMesterHasSpeciality(String mesterId) {
		String sql = "DELETE FROM mester_has_speciality WHERE id_mester = ?";
		template.update(sql, mesterId);
	}

}
