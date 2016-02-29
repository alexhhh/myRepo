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
public class Mester extends BaseModel {

	private String firstName;
	private String lastName;
	private String location;
	private String description;
	private Contact contact;
	private Speciality speciality;

	public Mester() {
	}

	public Mester(String idMester, String firstName, String lastName, String description, String location) {

		super(idMester);
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.location = location;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}

 
	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality( Speciality speciality) {
		this.speciality = speciality;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mester [idMester=" + getId() + ", fisrtName=" + firstName + ", lastName=" + lastName + ", description="
				+ description + ", location=" + location + ", contact=" + contact + "]";
	}

}
