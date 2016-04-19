/**
 * 
 */
package com.intern.alexx.model;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;

/**
 * @author malex
 *
 */

@ApiModel
public class Mester extends BaseModel {
	
	private String mesterUserId;
	private String firstName;
	private String lastName;
	private String location;
	private String description;
	private Contact contact;
	private Integer avgPrice;
	private Integer avgRating;
	private List<Speciality> speciality;

	public Mester() {
	}

	public Mester(String idMester, String firstName, String lastName, String description, Integer avgPrice,Integer avgRating, String location) {

		super(idMester);
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.avgPrice=avgPrice;
		this.avgRating=avgRating;
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

	public Integer getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Integer avgPrice) {
		this.avgPrice = avgPrice;
	}

	public Integer getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(Integer avgRating) {
		this.avgRating = avgRating;
	}

	public List<Speciality> getSpeciality() {
		return speciality;
	}

	public void setSpeciality(List<Speciality> speciality) {
		this.speciality = speciality;
	}

	public String getMesterUserId() {
		return mesterUserId;
	}

	public void setMesterUserId(String mesterUserId) {
		this.mesterUserId = mesterUserId;
	}

	@Override
	public String toString() {
		return "Mester [idMester=" + getId() +"  mesterUserId=" +mesterUserId+ ", fisrtName=" + firstName + ", lastName=" + lastName + ", description="
				+ description + ", location=" + location + ", contact=" + contact + ", Rating="+avgRating+", Price="+ avgPrice+  " specialities=" + speciality+ "]";
	}

}
