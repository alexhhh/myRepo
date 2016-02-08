/**
 * 
 */
package com.intern.alexx.model;

/**
 * @author malex
 *
 */
public class Mester extends BaseModel {

	private String firstName;
	private String lastName;
	private String location;
	private String description;
	private Contact contact;
	private Speciality speciality;
	private ReviewMester reviewMester;

	public Mester() {

	}

	public Mester(int idMester, String firstName, String lastName, String description, String location) {

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

	/**
	 * @return the speciality
	 */
	public Speciality getSpeciality() {
		return speciality;
	}

	/**
	 * @param speciality
	 *            the speciality to set
	 */
	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	/**
	 * @return the reviewMester
	 */
	public ReviewMester getReviewMester() {
		return reviewMester;
	}

	/**
	 * @param reviewMester
	 *            the reviewMester to set
	 */
	public void setReviewMester(ReviewMester reviewMester) {
		this.reviewMester = reviewMester;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mester [idMester=" + getId() + ", fisrtName=" + firstName + ", lastName=" + lastName + ", description="
				+ description + ", location=" + location + "]";
	}

}
