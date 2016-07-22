package com.ikon.alexx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikon.alexx.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByUserName(String userName);
	
	User findByUserNameAndPassword(String userName, String password);
	
	User findByUserNameAndEmail(String userName, String email);
}
