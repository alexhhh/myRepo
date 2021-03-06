package com.ikon.alexx.repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ikon.alexx.model.MesterSearchCriteria;

@Component
public class GenerateSql {

 
	public String createQueryForElements(MesterSearchCriteria searchCriteria) { 
		return new StringBuilder("SELECT m ").append(createString(searchCriteria)).toString();
	}

	public String createQueryForCountElements(MesterSearchCriteria searchCriteria) {  
		return new StringBuilder("SELECT COUNT(*) ").append(createString(searchCriteria)).toString();
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
		if (searchCriteria.getAvgRating() != null) {
			params.put("avgRating", searchCriteria.getAvgRating());
		}
		if (searchCriteria.getAvgPrice() != null) {
			params.put("avgPrice", searchCriteria.getAvgPrice());
		}
		return params;
	}

	private StringBuilder createString(MesterSearchCriteria searchCriteria) {  
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
				whereList.add(" c.telNr= :telNr ");
			}
		}
		if ((searchCriteria.getAvgRating() != null) || (searchCriteria.getAvgPrice() != null)) {
			if (searchCriteria.getAvgRating() != null) {
				whereList.add(" m.avgRating= :avgRating ");
			}
			if (searchCriteria.getAvgPrice() != null) {
				whereList.add(" m.avgPrice= :avgPrice ");
			}
		}
		return new StringBuilder("FROM Mester m ").append(String.join(" ", joinList)).append(String.join(" AND ", whereList));
	}

}