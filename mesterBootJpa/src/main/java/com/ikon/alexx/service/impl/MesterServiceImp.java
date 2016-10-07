package com.ikon.alexx.service.impl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ikon.alexx.converters.FullMesterConverter;
import com.ikon.alexx.converters.MesterConverter;
import com.ikon.alexx.entity.Contact;
import com.ikon.alexx.entity.Location;
import com.ikon.alexx.entity.Mester;
import com.ikon.alexx.entity.Speciality;
import com.ikon.alexx.model.AreaSearchCriteria;
import com.ikon.alexx.model.FullMester;
import com.ikon.alexx.model.MesterDTO;
import com.ikon.alexx.model.MesterSearchCriteria;
import com.ikon.alexx.model.MyPage;
import com.ikon.alexx.repository.ContactRepository;
import com.ikon.alexx.repository.LocationRepository;
import com.ikon.alexx.repository.MesterRepository;
import com.ikon.alexx.repository.SpecialityRepository;
import com.ikon.alexx.repository.UserRepository;
import com.ikon.alexx.service.MesterService;

@Transactional
@Component
public class MesterServiceImp implements MesterService {

	@Autowired
	private MesterRepository mesterRepo;

	@Autowired
	private MesterConverter mesterConv;

	@Autowired
	private FullMesterConverter fullMesterConv;

	@Autowired
	private ContactRepository contactRepo;

	@Autowired
	private SpecialityRepository specRepo;

	@Autowired
	private LocationRepository locationRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public void insertMester(MesterDTO mesterDTO) {
		saveEverytingForMester(setUserToMester(mesterDTO));
	}

	public void insertMester(Mester mester) {
		saveEverytingForMester(mester);
	}


	@Override
	public void updateMester(FullMester fullMester) { 
		mesterRepo.save(fullMesterConv.toEntity(fullMester));
	}

	@Override
	public void deleteMester(String mesterId) {
		if (mesterId == null) {
			throw new IllegalArgumentException("mester ID is null");
		}
		Mester mester = mesterRepo.findOne(mesterId);
		contactRepo.delete(mester.getContact().getId());
		locationRepo.delete(mester.getLocation().getId());
		mesterRepo.delete(mesterId);
	}

	@Override
	public FullMester getMesterById(String id) {
		return fullMesterConv.fromEntity(mesterRepo.findOne(id));
	}

	@Override
	public FullMester getMesterByUserId(String userId) {
		return fullMesterConv.fromEntity(mesterRepo.findByUserId(userId));
	}

	@Override
	public MyPage<FullMester> searchMester(MesterSearchCriteria searchCriteria) throws SQLException {
		return fullMesterConv.fromEntitiesPage(mesterRepo.searchForMester(searchCriteria));
	}

	@Override
	public List<FullMester> searchMesterByArea(AreaSearchCriteria areaSearchCriteria) throws SQLException {
		List<Mester> listMesteri = mesterRepo.searchMesterByArea(areaSearchCriteria);
		return fullMesterConv.fromEntities(listMesteri);
	}

	private void saveEverytingForMester(Mester mester) {
		mesterRepo.save(mester);
		contactRepo.save(setContactForMester(mester));
		locationRepo.save(setlocationForMester(mester));
		specRepo.save(setSpecialitiesForMester(mester));
	}

	private Contact setContactForMester(Mester mester) {
		Contact contact = contactRepo.findByMesterId(mester.getId());
		if (contact == null) {
			contact = new Contact();
		}
		contact.setMester(mester);
		mester.setContact(contact);
		return contact;
	}

	private Location setlocationForMester(Mester mester) {
		Location location = locationRepo.findByMesterId(mester.getId());
		if (location == null) {
			location = new Location();
		}
		location.setMester(mester);
		mester.setLocation(location);
		return location;
	}

	private Set<Speciality> setSpecialitiesForMester(Mester mester) {
		Set<Speciality> specialities = new HashSet<>();
		mester.setSpecialities(specialities);
		return specialities;
	}

	private Mester setUserToMester(MesterDTO mesterDTO) {
		Mester mester = mesterConv.toEntity(mesterDTO);
		mester.setUser(userRepo.findOne(mesterDTO.getUserId()));
		return mester;
	}

}
