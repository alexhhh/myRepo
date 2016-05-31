package com.intern.alexx.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.wordnik.swagger.annotations.ApiModel;

@ApiModel
public class FullReview extends BaseModel {

 
	private String idMester;
	private String idClient;
	private Integer price;
	private int rating;
	private String title;
	private String feedback;
	private String firstName;
	private String lastName;

	public FullReview() {
	}

	public FullReview(String idReviewMester, String lastName, String idClient, String title, Integer price, int rating,
			String feedback, String idMester, String firstName) {
		super(idReviewMester);
		this.idMester = idMester;
		this.firstName= firstName;
		this.lastName=lastName;
		this.idClient = idClient;
		this.title=title;
		this.feedback = feedback;
		this.rating = rating;
		this.price = price;
	}

	public enum Price {
		LOW(1), MEDIUM(2), HIGH(3);

		private Price(int aValue) {
			value = aValue;
		}

		@JsonValue
		public int getValue() {
			return value;
		}

		private final int value;

	}

	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */

	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback
	 *            the feedback to set
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIdMester() {
		return idMester;
	}

	public void setIdMester(String idMester) {
		this.idMester = idMester;
	}

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
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

	@Override
	public String toString() {
		return "FullReview [id=" + getId() + "idMester=" + idMester
				+ ", idClient=" + idClient + ", price=" + price + ", rating=" + rating + ", title=" + title
				+ ", feedback=" + feedback  + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	
}
