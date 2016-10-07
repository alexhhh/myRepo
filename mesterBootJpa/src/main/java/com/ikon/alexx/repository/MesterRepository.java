package com.ikon.alexx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikon.alexx.entity.Mester ;

public interface MesterRepository extends JpaRepository<Mester, String>,MesterSpecialQueryRepository{
	
	Mester findByUserId(String id);

}
