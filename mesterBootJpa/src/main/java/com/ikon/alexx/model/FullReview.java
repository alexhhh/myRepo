package com.ikon.alexx.model;
 
 
		
public class FullReview extends ReviewDTO  {
 
	private String firstName;
	private String lastName;

	public FullReview() {
	}
	
	public FullReview(String idReviewMester, String idMester, String idClient, String title, Integer price, int rating,
			String feedback, String firstName, String lastName) {
		super();
		this.firstName=firstName;
		this.lastName=lastName;
	}
  
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

 
	
}
