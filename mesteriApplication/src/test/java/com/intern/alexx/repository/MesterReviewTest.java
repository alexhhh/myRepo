package com.intern.alexx.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intern.alexx.model.ReviewMester; 
public class MesterReviewTest {

	public static void main(String[] args) throws JsonProcessingException {
		
		ObjectMapper review = new ObjectMapper();
		
		ReviewMester newReview= new ReviewMester();
		 
		newReview.setIdMester("10");
		newReview.setIdClient("5");
		 
		newReview.setRating(5);
		newReview.setFeedback("Awesome Update");
		System.out.println(review.writeValueAsString(newReview));
		
		 
	}
}
