package com.ikon.alexx.repository;


import org.springframework.data.jpa.repository.JpaRepository;
 
import com.ikon.alexx.entity.Token;

public interface TokenRepository extends JpaRepository<Token, String>{
	
	Token findByUserName(String userName);

}
