/**
 * 
 */
package com.intern.alexx.model;

import com.wordnik.swagger.annotations.ApiModel;

/**
 * @author malex
 *
 */

@ApiModel
public class Speciality extends BaseModel{

	
	
	private String specialityName;
	
	public Speciality (){}
	
	public Speciality (int idSpeciality, String specialityName){
		super(idSpeciality);
		this.specialityName=specialityName;
	}
 
	/**
	 * @return the specialityName
	 */
	public String getSpecialityName() {
		return specialityName;
	}

	/**
	 * @param specialityName the specialityName to set
	 */
	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Speciality [idSpeciality=" + getId()+"specialityName=" + specialityName   + "]";
	}



	 
	
}
