package com.ikon.alexx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikon.alexx.entity.Speciality;

public interface SpecialityRepository extends JpaRepository<Speciality, String> {

	public Speciality findBySpecialityName(String specialityName);
 
}
