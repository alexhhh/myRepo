/**
 * 
 */
package com.intern.alexx.model;

import com.wordnik.swagger.annotations.ApiModel;

/**
 * @author malex
 *
 */

@ApiModel
public class Contact extends BaseModel {

	private Integer IdMester;
	private String telNr;
	private String email;
	private String site;
	private String socialPlatform;

	 
	public Integer getIdMester() {
		return IdMester;
	}

	public void setIdMester(Integer idMester) {
		IdMester = idMester;
	}

	/**
	 * @return the telNr
	 */
	public String getTelNr() {
		return telNr;
	}

	/**
	 * @param telNr
	 *            the telNr to set
	 */
	public void setTelNr(String telNr) {
		this.telNr = telNr;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @param site
	 *            the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}

	public String getSocialPlatform() {
		return socialPlatform;
	}

	public void setSocialPlatform(String socialPlatform) {
		this.socialPlatform = socialPlatform;
	}

	@Override
	public String toString() {
		return "Contact [IdMester=" + IdMester + ", telNr=" + telNr + ", email=" + email + ", site=" + site
				+ ", socialPlatform=" + socialPlatform + "]";
	}

 

}
