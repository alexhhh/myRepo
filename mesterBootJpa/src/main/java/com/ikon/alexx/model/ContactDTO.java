package com.ikon.alexx.model;

public class ContactDTO {

	private String id;
	private String telNr;
	private String email;
	private String site;

	 private String  mesterId;
	
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

	public String getMesterId() {
		return mesterId;
	}

	public void setMesterId(String mesterId) {
		this.mesterId = mesterId;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", telNr=" + telNr + ", email=" + email + ", site=" + site + "]";
	}

}
