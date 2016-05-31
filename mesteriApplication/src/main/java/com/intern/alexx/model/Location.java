package com.intern.alexx.model;

public class Location extends BaseModel  {

	 
	private String mesterId;
	private String location;
	private double latitude;
	private double longitude;
	
	 
	 
	public String getMesterId() {
		return mesterId;
	}
	public void setMesterId(String mesterId) {
		this.mesterId = mesterId;
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
	@Override
	public String toString() {
		return "Location [id=" + getId() + ", mesterId=" + mesterId + ", location=" + location + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

}
