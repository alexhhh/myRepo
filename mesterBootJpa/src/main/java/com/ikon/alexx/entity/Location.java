package com.ikon.alexx.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue; 
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Location {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;

	private String location;
	private double latitude;
	private double longitude;

	@OneToOne
	private Mester mester;

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

	public Mester getMester() {
		return mester;
	}

	public void setMester(Mester mester) {
		this.mester = mester;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", location=" + location + ", latitude=" + latitude + ", longitude=" + longitude
				 + "]";
	}

}
