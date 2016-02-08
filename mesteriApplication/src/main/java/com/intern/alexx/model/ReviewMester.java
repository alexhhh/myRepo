/**
 * 
 */
package com.intern.alexx.model;

/**
 * @author malex
 *
 */
public class ReviewMester extends BaseModel {

	private String price;
	private int rating;
	private String feedback;

	public ReviewMester() {
	}

	public ReviewMester(int idReviewMester, String price, int rating, String feedback) {
		super(idReviewMester);
		this.feedback = feedback;
		this.rating = rating;
		this.price = price;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReviewMester [idReviewMester=" + getId() + ", price=" + price + ", rating=" + rating + ", feedback="
				+ feedback + "]";
	}
}
