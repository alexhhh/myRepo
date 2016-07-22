package com.ikon.alexx.model;

public class ContactDTO {

	private String id;
	private String telNr;
	private String email;
	private String site;

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

	@Override
	public String toString() {
		return "Contact [id=" + id + ", telNr=" + telNr + ", email=" + email + ", site=" + site + "]";
	}

}
