package com.ikon.alexx.model;

import java.util.List;

public class FullMester {

	private String id;
	private String userId;
	private String firstName;
	private String lastName;
	private String description;
	private Integer avgPrice;
	private Integer avgRating;
	private ContactDTO contact;
	private LocationDTO location;
	private List<SpecialityDTO> speciality;

	public FullMester() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public ContactDTO getContact() {
		return contact;
	}

	public void setContact(ContactDTO contact) {
		this.contact = contact;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public void setLocation(LocationDTO location) {
		this.location = location;
	}

	public List<SpecialityDTO> getSpeciality() {
		return speciality;
	}

	public void setSpeciality(List<SpecialityDTO> speciality) {
		this.speciality = speciality;
	}

}
