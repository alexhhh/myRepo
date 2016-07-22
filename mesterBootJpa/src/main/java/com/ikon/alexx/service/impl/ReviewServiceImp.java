package com.ikon.alexx.service.impl;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ikon.alexx.converters.ReviewConverter; 
import com.ikon.alexx.model.MesterDTO;
import com.ikon.alexx.model.ReviewDTO; 
import com.ikon.alexx.repository.ReviewRepository;
import com.ikon.alexx.service.MesterService;
import com.ikon.alexx.service.ReviewService;

@Transactional
@Component
public class ReviewServiceImp implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private ReviewConverter reviewConv;
	
	@Autowired
	private MesterService mesterService;
	
	@Override
	public void insertReviewMester(ReviewDTO review) {
		reviewRepo.save(reviewConv.toEntity(review));
		updateMesterAvgPriceAndRating(review.getMesterId());
	}

	@Override
	public void updateReviewMester(ReviewDTO review) {
		reviewRepo.save(reviewConv.toEntity(review));
		updateMesterAvgPriceAndRating(review.getMesterId());
	}

	@Override
	public void deleteReviewMester(String idReview) {
		reviewRepo.delete(idReview);
	}

	@Override
	public ReviewDTO getById(String idReview) {
		return reviewConv.fromEntity(reviewRepo.findOne(idReview));
	}

	@Override
	public Page<ReviewDTO> getReviewMasterPage(String idMester, Pageable pageable) {		 
		return reviewConv.fromEntitiesPage(reviewRepo.findByMesterId(idMester, pageable));
	}

	@Override
	public Page<ReviewDTO> getReviewAllMasterPage(Pageable pageable) {		 
		return reviewConv.fromEntitiesPage(reviewRepo.findAll(pageable));
	}

	@Override
	public Page<ReviewDTO> getAllReviewFromClient(String idClient, Pageable pageable) {		 
		return reviewConv.fromEntitiesPage(reviewRepo.findByClientId(idClient, pageable));
	}

	
	private MesterDTO updateMesterAvgPriceAndRating(String idMester) {
		MesterDTO mester = mesterService.getMesterById(idMester); 	 
//			mester.setAvgRating(reviewRepo.getAvgRatingForMester(idMester));
//			mester.setAvgPrice(reviewMesterRepository.getAvgPriceForMester(idMester));
//			mesterService.updateAvg(mester);
			return mester;
	}
	
	
}
