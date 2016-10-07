package com.ikon.alexx.model;

public class ReviewDTO {

	private String id;
	private String mesterId;
	private String clientId;
	private Integer price;
	private int rating;
	private String title;
	private String feedback;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMesterId() {
		return mesterId;
	}

	public void setMesterId(String mesterId) {
		this.mesterId = mesterId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "ReviewDTO [id=" + id + ", mesterId=" + mesterId + ", clientId=" + clientId + ", price=" + price
				+ ", rating=" + rating + ", title=" + title + ", feedback=" + feedback + "]";
	}
 
}
