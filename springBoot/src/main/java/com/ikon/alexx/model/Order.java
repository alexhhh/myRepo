package com.ikon.alexx.model;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;

	private Date orderDate;
	private long price;
	private String details;
	private long customerId;

	@ManyToOne
	private Customer customer;
	
	public Order() {
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {  
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", price=" + price + ", details=" + details
				+ ", customerId=" + customerId + "]";
	}

}
