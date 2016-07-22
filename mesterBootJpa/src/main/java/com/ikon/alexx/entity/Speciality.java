package com.ikon.alexx.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Speciality {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;

	private String specialityName;

	@ManyToMany 
	private List<Mester> mesteri;

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

	public List<Mester> getMesteri() {
		return mesteri;
	}

	public void setMesteri(List<Mester> mesteri) {
		this.mesteri = mesteri;
	}

	@Override
	public String toString() {
		return "Speciality [id=" + id + ", specialityName=" + specialityName + "]";
	} 

}
