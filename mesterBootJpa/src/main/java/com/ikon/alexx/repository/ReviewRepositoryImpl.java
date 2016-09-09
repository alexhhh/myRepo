package com.ikon.alexx.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.ikon.alexx.entity.Review;

public class ReviewRepositoryImpl implements ReviewSpecialQueryRepository {

	@Autowired
	EntityManager em;

	@Override
	public Integer getMesterPriceAVGs(String mesterId) {
		TypedQuery<Double> query = em.createNamedQuery(Review.MESTER_PRICE_AVG, Double.class);
		query.setParameter("mester_id", mesterId);
		return query.getSingleResult().intValue();
	}

	@Override
	public Integer getMesterRatingAVGs(String mesterId) {
		TypedQuery<Double> query = em.createNamedQuery(Review.MESTER_RATING_AVG, Double.class);
		query.setParameter("mester_id", mesterId);
		return query.getSingleResult().intValue();
	}

}
