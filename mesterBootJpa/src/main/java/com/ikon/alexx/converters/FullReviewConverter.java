package com.ikon.alexx.converters;

import org.springframework.stereotype.Component;

import com.ikon.alexx.entity.Review;
import com.ikon.alexx.model.FullReview; 

@Component
public class FullReviewConverter extends BaseConverter<FullReview , Review> {

	@Override
	public Review toEntity(FullReview pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FullReview fromEntity(Review entity) {
		FullReview pojo= new FullReview();
		pojo.setId(entity.getId());
		pojo.setTitle(entity.getTitle());
		pojo.setPrice(entity.getPrice());
		pojo.setRating(entity.getRating());
		pojo.setFeedback(entity.getFeedback());
		pojo.setClientId(entity.getClient().getId());
		pojo.setMesterId(entity.getMester().getId());
		pojo.setFirstName(entity.getMester().getFirstName());
		pojo.setLastName(entity.getMester().getLastName());
		return pojo; 
	}

}
