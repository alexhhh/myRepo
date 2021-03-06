package com.ikon.alexx.converters;

import org.springframework.stereotype.Component;

import com.ikon.alexx.entity.Contact;
import com.ikon.alexx.model.ContactDTO;

@Component
public class ContactConverter extends BaseConverter<ContactDTO, Contact>{

	@Override
	public Contact toEntity(ContactDTO pojo) {
		Contact entity = new Contact();
		entity.setId(pojo.getId());
		entity.setTelNr(pojo.getTelNr());
		entity.setEmail(pojo.getEmail());
		entity.setSite(pojo.getSite());
		//entity.setMester(pojo.getMesterId());
		return entity;
	}

	@Override
	public ContactDTO fromEntity(Contact entity) {
		ContactDTO pojo = new ContactDTO();
		pojo.setId(entity.getId());
		pojo.setTelNr(entity.getTelNr());
		pojo.setEmail(entity.getEmail());
		pojo.setSite(entity.getSite()); 
		pojo.setMesterId(entity.getMester().getId());
		return pojo;
	}

}
