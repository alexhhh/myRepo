package com.ikon.alexx.model;

public class MesterDTO {

	private String id;
	private String mesterUserId;
	private String firstName;
	private String lastName;
	private String description;
	private Integer avgPrice;
	private Integer avgRating;

	public MesterDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMesterUserId() {
		return mesterUserId;
	}

	public void setMesterUserId(String mesterUserId) {
		this.mesterUserId = mesterUserId;
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

	@Override
	public String toString() {
		return "Mester [id=" + id + ", mesterUserId=" + mesterUserId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", description=" + description + ", avgPrice=" + avgPrice + ", avgRating=" + avgRating
				+ " ]";
	}
}
