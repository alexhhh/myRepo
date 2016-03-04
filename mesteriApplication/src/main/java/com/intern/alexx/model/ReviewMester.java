/**
 * 
 */
package com.intern.alexx.model;

 
import com.fasterxml.jackson.annotation.JsonValue;
 
import com.wordnik.swagger.annotations.ApiModel;

/**
 * @author malex
 *
 */

@ApiModel
public class ReviewMester extends BaseModel {

	private String idMester;
	private String idClient;
	private  Price price;
	private int rating;
	private String feedback;

	public ReviewMester() {
	}

	public ReviewMester(String idReviewMester, String idMester, String idClient, Price price, int rating,
			String feedback) {
		super(idReviewMester);
		this.idMester = idMester;
		this.idClient = idClient;
		this.feedback = feedback;
		this.rating = rating;
		this.price = price;
	}

	 
	public enum Price {
		LOW(1), MEDIUM(2), HIGH(3);
 
		private Price(  int aValue) {
			 value = aValue;
		}

		@JsonValue
		public int getValue() {
			return value;
		}
		
		private  final int value;
 
	}

	/**
	 * @return the price
	 */
	public Price getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	
 
	public void setPrice(Price price) {
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


	@Override
	public String toString() {
		return "ReviewMester [idReviewMester=" + getId() + ", price=" + price + ", rating=" + rating + ", feedback="
				+ feedback + "]";
	}
}
