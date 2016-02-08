/**
 * 
 */
package com.intern.alexx.model;

/**
 * @author malex
 *
 */
public class Client extends BaseModel {

 
	private String firstName;
	private String lastName;
	
 

	/**
	 * @return the idClient
	 */
	public  Client(int idClient,String firstName,String lastName ) {
		 super(idClient);
		 this.firstName=firstName;
		 this.lastName=lastName;
	}
 
	/**
	 * @return the fisrtName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param fisrtName the fisrtName to set
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
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [ idClient=" +  getId()  +"firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	
	
}
