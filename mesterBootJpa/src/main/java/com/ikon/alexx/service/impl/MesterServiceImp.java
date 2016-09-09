package com.ikon.alexx.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ikon.alexx.converters.MesterConverter;
import com.ikon.alexx.entity.Contact;
import com.ikon.alexx.entity.Location;
import com.ikon.alexx.entity.Mester;
import com.ikon.alexx.model.AreaSearchCriteria;
import com.ikon.alexx.model.MesterDTO;
import com.ikon.alexx.model.MesterSearchCriteria;
import com.ikon.alexx.model.MyPage;
import com.ikon.alexx.repository.ContactRepository;  
import com.ikon.alexx.repository.LocationRepository;
import com.ikon.alexx.repository.MesterRepository;
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
	private ContactRepository contactRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private LocationRepository locationRepo;

	@Override
	public void insertMester(MesterDTO mesterDTO) {
		Mester mester = mesterConv.toEntity(mesterDTO); // location + contact
		mester.setUser(userRepo.findOne(mesterDTO.getUserId()));
		setEverytingForMester(mester);
	}

	@Override
	public void updateMester(MesterDTO mesterDTO) {
		Mester mester = mesterConv.toEntity(mesterDTO);
		mester.setUser(userRepo.findOne(mesterDTO.getUserId()));
		mesterRepo.save(mester);  // aci nu tre si loc + content
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
	public MesterDTO getMesterById(String id) {
		return mesterConv.fromEntity(mesterRepo.findOne(id));
	}

	@Override
	public MesterDTO getMesterByUserId(String userId) {
		return mesterConv.fromEntity(mesterRepo.findByUserId(userId));
	}

	@Override
	public MyPage<MesterDTO> searchMester(MesterSearchCriteria searchCriteria ) throws SQLException {	
	 	 
		return mesterConv.fromEntitiesPage(mesterRepo.searchForMester(searchCriteria));
	}

	@Override
	public List<MesterDTO> searchMesterByArea(AreaSearchCriteria areaSearchCriteria) throws SQLException {		
		 List<Mester> listMesteri =	mesterRepo.searchMesterByArea(areaSearchCriteria);
//		  // de ce setContact? Locatie si mail cred
		return mesterConv.fromEntities(listMesteri);
	}
 
	private void setEverytingForMester(Mester mester) {
		Contact contact = new Contact();
		contact.setMester(mester);
		mester.setContact(contact);
		Location location = new Location();
		location.setMester(mester);
		mester.setLocation(location); 
		mesterRepo.save(mester);
		contactRepo.save(contact);
		locationRepo.save(location);
	}

}
