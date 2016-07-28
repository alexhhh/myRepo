package com.ikon.alexx.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue; 
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Contact {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid2")
	private String id;

	private String telNr;
	private String email;
	private String site;
	
	@OneToOne
	private Mester mester;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTelNr() {
		return telNr;
	}

	public void setTelNr(String telNr) {
		this.telNr = telNr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Mester getMester() {
		return mester;
	}

	public void setMester(Mester mester) {
		this.mester = mester;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", telNr=" + telNr + ", email=" + email + ", site=" + site  + "]";
	}

}
