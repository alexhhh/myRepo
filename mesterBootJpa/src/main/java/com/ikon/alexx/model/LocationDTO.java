package com.ikon.alexx.model;

public class LocationDTO {

	private String id;
	private String location;
	private double latitude;
	private double longitude;
	private String mesterId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getMesterId() {
		return mesterId;
	}

	public void setMesterId(String mesterId) {
		this.mesterId = mesterId;
	}

	@Override
	public String toString() {
		return "LocationDTO [id=" + id + ", location=" + location + ", latitude=" + latitude + ", longitude="
				+ longitude + ", mesterId=" + mesterId + "]";
	}

 

}
