package com.ikon.alexx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikon.alexx.entity.Location;

public interface LocationRepository extends JpaRepository<Location, String> {
 
}
