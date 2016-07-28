package com.ikon.alexx.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ikon.alexx.converters.MesterConverter;
import com.ikon.alexx.entity.Mester;
import com.ikon.alexx.model.AreaSearchCriteria;
import com.ikon.alexx.model.MesterDTO;
import com.ikon.alexx.model.MesterSearchCriteria;
import com.ikon.alexx.repository.ContactRepository;
import com.ikon.alexx.repository.MesterRepository;
import com.ikon.alexx.service.LocationService;
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
	private LocationService locationService;
	
	@Override
	public void insertMester(MesterDTO mester) {
		//mesterRepo.save(mester);
		 mesterRepo.save(mesterConv.toEntity(mester));
	}

	@Override
	public void updateMester(MesterDTO mester) {
		 mesterRepo.save(mesterConv.toEntity(mester));
	}

	@Override
	public void deleteMester(String mesterId) {
		if (mesterId == null) {
			throw new IllegalArgumentException("mester ID is null");
		}
		//LOGGER.info("--Service-- Delete mester with Id: " + mesterId);
		//mesterRepo.deleteFromMesterHasSpeciality(mesterId);
		Mester mester = mesterRepo.findOne(mesterId);
		contactRepo.delete(mester.getContact().getId());
		locationService.delete(mester.getLocation().getId());		 
		mesterRepo.delete(mesterId);
	}

	@Override
	public MesterDTO getMesterById(String id) {
		return mesterConv.fromEntity(mesterRepo.findOne(id));	
	}

	@Override
	public Page<MesterDTO> searchMester(MesterSearchCriteria searchCriteria, Pageable pageable) throws SQLException {
		  /// ????????????
		return null;
	}

	@Override
	public List<MesterDTO> searchMesterByArea(AreaSearchCriteria areaSearchCriteria) throws SQLException {
		List<MesterDTO> listMesteri = mesterConv.fromEntities(mesterRepo.findAll()); //searchMesterByArea(areaSearchCriteria);
//		for (Mester mester : listMesteri) {
//			mester.setContact(contactRepo.findByMesterId((mester.getId())));
//		}
		return listMesteri;
	 
	}

	@Override
	public void updateAvg(MesterDTO mester) {
		// TODO Auto-generated method stub

	}
 

}
