package com.ikon.alexx.converters;

import org.springframework.stereotype.Component;

import com.ikon.alexx.entity.Review;
import com.ikon.alexx.model.ReviewDTO;

@Component
public class ReviewConverter  extends BaseConverter<ReviewDTO, Review> {

	@Override
	public Review toEntity(ReviewDTO pojo) {
		Review entity= new Review();
		entity.setId(pojo.getId());		 
		entity.setTitle(pojo.getTitle());
		entity.setPrice(pojo.getPrice());
		entity.setRating(pojo.getRating());
		entity.setFeedback(pojo.getFeedback());
		return entity;
	}

	@Override
	public ReviewDTO fromEntity(Review entity) {
		ReviewDTO pojo= new ReviewDTO();
		pojo.setId(entity.getId());
		pojo.setTitle(entity.getTitle());
		pojo.setPrice(entity.getPrice());
		pojo.setRating(entity.getRating());
		pojo.setFeedback(entity.getFeedback());
		pojo.setClientId(entity.getClient().getId());
		pojo.setMesterId(entity.getMester().getId());
		return pojo;
	}

}
