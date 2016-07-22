package com.ikon.alexx.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ikon.alexx.entity.Review;
 
public interface ReviewRepository extends JpaRepository<Review, String>{

	Page<Review> findByMesterId(String mesterId, Pageable pageable);

	Page<Review> findAll(Pageable pageable);

	Page<Review> findByClientId(String clientId, Pageable pageable);
	
	// scoatem avg-u pt rating si price pt ca ala o sa il avem in mester
	
	// si tre sa vedem cum facem cu fullReview-ul
}
