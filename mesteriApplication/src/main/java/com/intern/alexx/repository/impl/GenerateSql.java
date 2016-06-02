package com.intern.alexx.repository.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.intern.alexx.model.AreaSearchCriteria;
import com.intern.alexx.model.MesterSearchCriteria;

@Component
public class GenerateSql {

	private String sql;
	private StringBuilder query; 
	
	public String createQueryForAreaSearch(AreaSearchCriteria areaSearchCriteria) {
		return  "SELECT m.* FROM mester AS m  LEFT JOIN location AS l ON m.id=l.mester_id WHERE l.latitude BETWEEN "+
				areaSearchCriteria.getMinLat()+" AND "+ areaSearchCriteria.getMaxLat()+ " AND l.longitude  BETWEEN "+
				areaSearchCriteria.getMinLng()+" AND "+ areaSearchCriteria.getMaxLng() + " ;";	 
	}
	 
	public String createQueryForElements(MesterSearchCriteria searchCriteria) {
		sql = null;
		query = new StringBuilder("SELECT ");
		String limit;
		StringBuilder from = createString(searchCriteria);
		if (searchCriteria.getPageSize() == null) {
			searchCriteria.setPageSize(10);
		}
		if (searchCriteria.getPageNumber() == null) {
			searchCriteria.setPageNumber(1);
		} 
		limit = " LIMIT " + (searchCriteria.getPageSize() * (searchCriteria.getPageNumber() - 1)) + " , "
				+ (searchCriteria.getPageSize()) + " ;";

		sql = query.append(" m.* ").append(from).append(limit).toString();
		return sql;
	}

	public String createQueryForCountElements(MesterSearchCriteria searchCriteria) {
		sql = null;
		StringBuilder query = new StringBuilder("SELECT ");
		StringBuilder from = createString(searchCriteria);
		sql = query.append("COUNT(*) AS total ").append(from).append(" ;").toString();
		return sql;
	}

	private StringBuilder createString(MesterSearchCriteria searchCriteria) {
		String join, on, where ;
		StringBuilder from = new StringBuilder("FROM mester as m ");
		List<String> joinList = new LinkedList<String>();
		joinList.add(" JOIN user AS u ");
		List<String> onList = new LinkedList<String>();
		onList.add(" ON u.id=m.id ");
		List<String> whereList = new LinkedList<String>();
		whereList.add("WHERE u.enable=1 " );

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
			joinList.add("JOIN mester_has_speciality as mhs JOIN speciality as s ");
			onList.add(" m.id = mhs.id_mester ");   
			onList.add(" s.id = mhs.id_speciality ");
			whereList.add(" s.speciality_name=:speciality_name ");
		}
		if ((searchCriteria.getEmail() != null) || (searchCriteria.getPhoneNumber() != null)) {
			joinList.add("JOIN contact AS c ");
			onList.add(" m.id=c.id_mester ");
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
		on = String.join(" AND ", onList);
		join = String.join(" ", joinList);


			return from = from.append(join).append(on).append(where) ;
		 
	}
 
}
