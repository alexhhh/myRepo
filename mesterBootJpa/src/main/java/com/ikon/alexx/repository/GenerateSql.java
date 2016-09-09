package com.ikon.alexx.repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ikon.alexx.model.MesterSearchCriteria;

@Component
public class GenerateSql {

	private String sql;
	private StringBuilder query;

	public String createQueryForElements(MesterSearchCriteria searchCriteria) {
		sql = null;
		query = new StringBuilder("SELECT m ");
		StringBuilder from = createString(searchCriteria);
		sql = query.append(from).toString();
		return sql;
	}

	public String createQueryForCountElements(MesterSearchCriteria searchCriteria) {
		sql = null;
		StringBuilder query = new StringBuilder("SELECT ");
		StringBuilder from = createString(searchCriteria);
		sql = query.append("COUNT(*) ").append(from).toString();
		return sql;
	}

	public Map<String, Object> createQueryParam(MesterSearchCriteria searchCriteria) {

		Map<String, Object> params = new HashMap<>();
		if (searchCriteria.getFirstName() != null) {
			params.put("firstName", searchCriteria.getFirstName());
		}
		if (searchCriteria.getLastName() != null) {
			params.put("lastName", searchCriteria.getLastName());
		}
		if (searchCriteria.getLocation() != null) {
			params.put("location", searchCriteria.getLocation());
		}

		if (searchCriteria.getSpecialityName() != null) {
			params.put("specialityName", searchCriteria.getSpecialityName());
		}
		if (searchCriteria.getEmail() != null) {
			params.put("email", searchCriteria.getEmail());
		}
		if (searchCriteria.getPhoneNumber() != null) {
			params.put("telNr", searchCriteria.getPhoneNumber());
		}

		if (searchCriteria.getRating() != null) {
			params.put("avgRating", searchCriteria.getRating());
		}
		if (searchCriteria.getPrice() != null) {
			params.put("avgPrice", searchCriteria.getPrice());
		}
		return params;
	}

	private StringBuilder createString(MesterSearchCriteria searchCriteria) {
		String join, where;
		StringBuilder from = new StringBuilder("FROM Mester m ");
		List<String> joinList = new LinkedList<String>();
		joinList.add("left outer join m.user u ");
		List<String> whereList = new LinkedList<String>();
		whereList.add("WHERE u.isEnable=1 ");

		if (searchCriteria.getFirstName() != null) {
			whereList.add(" m.firstName= :firstName ");
		}
		if (searchCriteria.getLastName() != null) {
			whereList.add(" m.lastName= :lastName ");
		}
		if (searchCriteria.getLocation() != null) {
			joinList.add("left outer join m.location  l ");
			whereList.add(" l.location= :location ");
		}

		if (searchCriteria.getSpecialityName() != null) {
			joinList.add("left outer join m.specialities  s ");
			whereList.add(" s.specialityName= :specialityName ");
		}
		if ((searchCriteria.getEmail() != null) || (searchCriteria.getPhoneNumber() != null)) {
			joinList.add("left outer join m.contact  c ");
			if (searchCriteria.getEmail() != null) {
				whereList.add(" c.email= :email ");
			}
			if (searchCriteria.getPhoneNumber() != null) {
				whereList.add(" c.telNr= :phoneNumber ");
			}
		}

		if ((searchCriteria.getRating() != null) || (searchCriteria.getPrice() != null)) {
			if (searchCriteria.getRating() != null) {
				whereList.add(" m.avgRating= :rating ");
			}
			if (searchCriteria.getPrice() != null) {
				whereList.add(" m.avgPrice= :price ");
			}
		}

		where = String.join(" AND ", whereList);
		join = String.join(" ", joinList);

		return from = from.append(join).append(where);

	}

}