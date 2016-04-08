package com.intern.alexx.repository.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.intern.alexx.model.MesterSearchCriteria;

@Component
public class GenerateSql {

	private String sql;
	private StringBuilder query;

	public String createQueryForElements(MesterSearchCriteria searchCriteria) {
		sql = null;
		query = new StringBuilder("SELECT ");
		String limit;
		StringBuilder from = createString(searchCriteria);
		if (searchCriteria.getPageSize() == null){searchCriteria.setPageSize(10);}
		if (searchCriteria.getPageNumber() == null){searchCriteria.setPageNumber(1);}
		//if ((searchCriteria.getPageSize() != null) && (searchCriteria.getPageNumber() != null)) {
		limit = " LIMIT " + (searchCriteria.getPageSize() * (searchCriteria.getPageNumber() - 1)) + " , "
					+ (searchCriteria.getPageSize()) + " ;";
		
		sql = query.append(" * ").append(from).append(limit).toString();
		return sql;
	}

	public String createQueryForCountElements(MesterSearchCriteria searchCriteria) {
		sql = null;
		StringBuilder query = new StringBuilder("SELECT ");
		StringBuilder from = createString(searchCriteria);
		sql = query.append("COUNT(*) AS total ").append(from).append(" ;").toString();
		return sql;
	}

	public StringBuilder createString(MesterSearchCriteria searchCriteria) {
		String join, where;
		StringBuilder from = new StringBuilder("FROM mester as m ");
		List<String> joinList = new LinkedList<String>();
		List<String> whereList = new LinkedList<String>();

		if (searchCriteria.getFirstName() != null) {
			whereList.add(" m.first_name=:first_name ");
		}
		if (searchCriteria.getLastName() != null) {
			whereList.add(" m.last_name=:last_name ");
		}
		if (searchCriteria.getLocation() != null) {
			whereList.add(" m.location=:location ");
		}

		if (searchCriteria.getSpecialityName() != null) {
			joinList.add("JOIN mester_has_speciality as mhs JOIN speciality as s "
					+ "ON m.id = mhs.id_mester AND s.id = mhs.id_speciality ");
			whereList.add(" s.speciality_name=:speciality_name ");
		}

		if ((searchCriteria.getEmail() != null) || (searchCriteria.getPhoneNumber() != null)) {
			joinList.add("JOIN contact AS c ON m.id=c.id_mester ");

			if (searchCriteria.getEmail() != null) {
				whereList.add(" c.email=:email ");
			}
			if (searchCriteria.getPhoneNumber() != null) {
				whereList.add(" c.numar_telefon=:numar_telefon ");
			}

		}

		if ((searchCriteria.getRating() != null) || (searchCriteria.getPrice() != null)) {
			 

			if (searchCriteria.getRating() != null) {
				whereList.add(" m.avg_rating=:rating ");
			}
			if (searchCriteria.getPrice() != null) {
				whereList.add(" m.avg_price=:price ");
			}
		}

		where = String.join(" AND ", whereList);
		join = String.join(" ", joinList);

		if (whereList.size() != 0) {
			return from = from.append(join).append(" WHERE ").append(where);
		} else {
			return from;
		}
	}
}
