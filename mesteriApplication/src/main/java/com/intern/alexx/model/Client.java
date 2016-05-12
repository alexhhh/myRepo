/**
 * 
 */
package com.intern.alexx.model;

/**
 * @author malex
 *
 */
public class Client extends BaseModel {

	private String clientUserId;
	private String firstName;
	private String lastName;

	/**
	 * @return the idClient
	 */
	public Client(String idClient, String firstName, String lastName, String clientUserId) {
		super(idClient);
		this.firstName = firstName;
		this.lastName = lastName;
		this.clientUserId=clientUserId;
	}
	
	public Client(){}
	
	/**
	 * @return the fisrtName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param fisrtName
	 *            the fisrtName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	@Override
	public String toString() {
		return "Client [clientUserId=" + clientUserId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
 

}
