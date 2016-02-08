package com.intern.alexx.repository.impl;


import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.intern.alexx.model.MesterSearchCriteria;


@Component
public class GenerateSql {

	private String sql=null;

	public String createString(MesterSearchCriteria searchCriteria) {

		StringBuilder query = new StringBuilder("SELECT mester.*  ");
		String from = "FROM mester AS m ", on, where, theOn=""  ;
		StringBuilder join = new StringBuilder(" ");
		List<String> onList = new LinkedList<String>();
		List<String> whereList = new LinkedList<String>(); 
		 

		if (searchCriteria.getFirstName() != null) {
			whereList.add(" m.first_name= ? ");
		}
		if (searchCriteria.getLastName() != null) {
			whereList.add(" m.last_name= ? ");
		}
		if (searchCriteria.getLocation() != null) {
			whereList.add(" m.location= ? ");
		}

		if (searchCriteria.getSpecialityName() != null) {
			// query.append(", speciality.speciality_name ");			
			join.append("JOIN mester_has_speciality as mhs JOIN speciality as s  ");
			onList.add(" (m.id = mhs.id_mester) AND (s.id = mhs.id_speciality) ");
			whereList.add(" s.speciality_name= ? ");
		}

		if ((searchCriteria.getEmail() != null) || (searchCriteria.getPhoneNumber() != null)) {
			join.append("JOIN contact AS c  ");
			onList.add(" m.id=c.id_mester ");

			if (searchCriteria.getEmail() != null) {
				// query.append(", contact.email ");
				whereList.add(" c.email= ? ");
			}

			if (searchCriteria.getPhoneNumber() != null) {
				// query.append(", contact.numar_telefon ");
				whereList.add(" c.numar_telefon= ? ");
			}

		}

		if ((searchCriteria.getRating() != null) || (searchCriteria.getPrice() != null)) {
			join.append("JOIN review_mester AS rm  ");
			onList.add(" m.id=rm.id_mester ");

			if (searchCriteria.getRating() != null) {
				// query.append(", review_mester.rating ");
				whereList.add(" rm.rating= ? ");
			}

			if (searchCriteria.getPrice() != null) {
				// query.append(", review_mester.price ");
				whereList.add(" rm.price= ? ");
			}

		}


		
			where = String.join(" AND ", whereList);
			
			if(onList.size() !=0){
				theOn=" ON ";
			}
			on = String.join(" AND ", onList);
		
		if(whereList.size()!=0){		
		sql = query.append(from).append(join).append(theOn).append(on).append(" WHERE ").append(where).append(" ;").toString();
		}
		else sql=query.append(from).append(" ;").toString();
		
		return sql;

	}

}
