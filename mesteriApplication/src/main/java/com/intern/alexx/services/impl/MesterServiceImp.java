/**
 * 
 */
package com.intern.alexx.services.impl;

import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.repository.MesterRepository;
import com.intern.alexx.services.MesterService;

/**
 * @author malex
 * 
 *
 */
@Component
public class MesterServiceImp implements MesterService {

	

	@Autowired
	private MesterRepository mesterRepository;

	public Collection<MyPage> searchMester(Mester mester, int pageSize, int pageNumber) {
		List<MyPage> content = new ArrayList<MyPage>();
		
		
		
		return null;
	}
	
	public Collection<Mester> searchMesterPage(Mester mester, int pageSize, int pageNumber) {
		List<Mester> content = mesterRepository.findAll();
		int index= pageSize*(pageNumber-1);
		int i=0;
		
		Iterator<Mester> it = content.iterator();
		
		while (i<index && it.hasNext()){
			it.next();
			it.remove();
			i++;
		}
		while (it.hasNext() && i<index+pageSize){
			it.next();
			i++;
		}
		while(it.hasNext()){
			it.next();
			it.remove();
		}
		
		
		
		return content;
	}

 

	public  Collection<String> searchMester(MesterSearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
