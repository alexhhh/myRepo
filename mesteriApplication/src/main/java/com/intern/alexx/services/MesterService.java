/**
 * 
 */
package com.intern.alexx.services;

import java.util.Collection;

import com.intern.alexx.model.MesterSearchCriteria;

 

/**
 * @author malex
 *
 */
public interface MesterService {

	
	 Collection<String> searchMester(MesterSearchCriteria searchCriteria);
	
	//TODO add crud methods 
	
}
