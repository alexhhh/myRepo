package com.ikon.alexx.model;

public class SpecialityDTO {

	private String id;

	private String specialityName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	@Override
	public String toString() {
		return "Speciality [id=" + id + ", specialityName=" + specialityName + "]";
	}

}
