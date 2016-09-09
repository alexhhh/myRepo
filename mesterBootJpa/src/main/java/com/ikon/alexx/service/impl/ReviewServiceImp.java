package com.ikon.alexx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ikon.alexx.converters.FullReviewConverter;
import com.ikon.alexx.converters.ReviewConverter;
import com.ikon.alexx.entity.Mester;
import com.ikon.alexx.entity.Review;
import com.ikon.alexx.model.FullReview;
import com.ikon.alexx.model.MyPage;
import com.ikon.alexx.model.ReviewDTO;
import com.ikon.alexx.repository.ClientRepository;
import com.ikon.alexx.repository.MesterRepository;
import com.ikon.alexx.repository.ReviewRepository;
import com.ikon.alexx.service.ReviewService;

@Transactional
@Component
public class ReviewServiceImp implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private ReviewConverter reviewConv;

	@Autowired
	private FullReviewConverter fullReviewConv;

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private MesterRepository mesterRepo;

	@Override
	public void insertReviewMester(ReviewDTO reviewDTO) {
		Review review = reviewConv.toEntity(reviewDTO);
		review.setClient(clientRepo.findOne(reviewDTO.getClientId()));
		review.setMester(mesterRepo.findOne(reviewDTO.getMesterId()));
		reviewRepo.save(review);
		updateMesterAvgPriceAndRating(review);
	}

	@Override
	public void updateReviewMester(ReviewDTO reviewDTO) {
		Review review = reviewConv.toEntity(reviewDTO);
		review.setClient(clientRepo.findOne(reviewDTO.getClientId()));
		review.setMester(mesterRepo.findOne(reviewDTO.getMesterId()));
		reviewRepo.save(review);
		updateMesterAvgPriceAndRating(review);
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
	public MyPage<ReviewDTO> getReviewMasterPage(String idMester, Integer pageSize, Integer pageNumber) {
		PageRequest pageReq = new PageRequest(pageNumber, pageSize);
		return reviewConv.fromEntitiesPage(reviewRepo.findByMesterId(idMester, pageReq));
	}

	@Override
	public MyPage<ReviewDTO> getReviewAllMasterPage(Integer pageSize, Integer pageNumber) {
		PageRequest pageReq = new PageRequest(pageNumber, pageSize);
		Page<Review> reviewsPage = reviewRepo.findAll(pageReq);
		return reviewConv.fromEntitiesPage(reviewsPage);
	}

	@Override
	public MyPage<ReviewDTO> getAllReviewFromClient(String idClient, Integer pageSize, Integer pageNumber) {
		PageRequest pageReq = new PageRequest(pageNumber, pageSize);
		return reviewConv.fromEntitiesPage(reviewRepo.findByClientId(idClient, pageReq));
	}

	@Override
	public MyPage<FullReview> getAllFullReviewsPage(Integer pageSize, Integer pageNumber) {
		PageRequest pageReq = new PageRequest(pageNumber, pageSize);
		return fullReviewConv.fromEntitiesPage(reviewRepo.findAll(pageReq));
	}

	@Override
	public MyPage<FullReview> getAllFullReviewsFromClient(String idClient, Integer pageSize, Integer pageNumber) {
		PageRequest pageReq = new PageRequest(pageNumber, pageSize);
		return fullReviewConv.fromEntitiesPage(reviewRepo.findByClientId(idClient, pageReq));
	}

	private Mester updateMesterAvgPriceAndRating(Review review) {
		Mester mester = review.getMester();
		mester.setAvgRating(reviewRepo.getMesterRatingAVGs(mester.getId()));
		mester.setAvgPrice(reviewRepo.getMesterPriceAVGs(mester.getId()));
		mesterRepo.save(mester);
		return mester;
	}

}
