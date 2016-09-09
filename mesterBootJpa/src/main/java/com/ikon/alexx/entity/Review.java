package com.ikon.alexx.entity;

 
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.GenericGenerator;
 
@Entity
@NamedQueries({
@NamedQuery(query = "SELECT ROUND(AVG(price)) FROM Review WHERE mester_id= :mester_id", name ="price_mester_avg"),
@NamedQuery(query = "SELECT ROUND(AVG(rating)) FROM Review WHERE mester_id= :mester_id", name ="rating_mester_avg")
})
public class Review {

	public static String MESTER_PRICE_AVG = "price_mester_avg";
	public static String MESTER_RATING_AVG = "rating_mester_avg";
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid2")
	private String id;

	private Integer price;
	private int rating;
	private String title;
	private String feedback;

	@ManyToOne 
	private Mester mester;

	@ManyToOne
	private Client client;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Mester getMester() {
		return mester;
	}

	public void setMester(Mester mester) {
		this.mester = mester;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", price=" + price + ", rating=" + rating + ", title=" + title + ", feedback="
				+ feedback + ", mester=" + mester + ", client=" + client + "]";
	}

}
