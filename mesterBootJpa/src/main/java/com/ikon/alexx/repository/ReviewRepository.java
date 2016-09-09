package com.ikon.alexx.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ikon.alexx.entity.Review;
 
public interface ReviewRepository extends JpaRepository<Review, String>, ReviewSpecialQueryRepository{

	Page<Review> findByMesterId(String mesterId, Pageable pageable);

	Page<Review> findAll(Pageable pageable);

	Page<Review> findByClientId(String clientId, Pageable pageable);
}
