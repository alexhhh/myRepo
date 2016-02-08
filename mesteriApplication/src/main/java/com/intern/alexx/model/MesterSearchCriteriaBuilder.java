package com.intern.alexx.model;

public class MesterSearchCriteriaBuilder {
	
	
	private MesterSearchCriteria msc;
	
	public MesterSearchCriteriaBuilder() {

		msc = new MesterSearchCriteria();
	}

	public MesterSearchCriteriaBuilder setFirstName (String fisrtName) {
		msc.setFirstName(fisrtName);
		return this;

	}
	public MesterSearchCriteriaBuilder setLastName (String lastName) {
		msc.setLastName(lastName);
		return this;

	}
	public MesterSearchCriteriaBuilder setLocation(String location) {
		msc.setLocation(location);
		return this;

	}
	public MesterSearchCriteriaBuilder SetSpecialityName(String specialityName) {
		msc.setSpecialityName(specialityName);
		return this;

	}
	public MesterSearchCriteriaBuilder setEmail(String email) {
		msc.setEmail(email);
		return this;

	}
	public MesterSearchCriteriaBuilder setPhoneNumber(String phoneNumber) {
		msc.setPhoneNumber(phoneNumber);
		return this;

	}
	public MesterSearchCriteriaBuilder setRating(Integer rating) {
		msc.setRating(rating);
		return this;

	}
	public MesterSearchCriteriaBuilder setPrice(String price) {
		msc.setPrice(price);
		return this;

	}

	public MesterSearchCriteria create() {

		MesterSearchCriteria msc = this.msc;
		this.msc = new MesterSearchCriteria();
		return msc;
	}

}
