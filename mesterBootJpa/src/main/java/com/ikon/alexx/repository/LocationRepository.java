package com.ikon.alexx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikon.alexx.entity.Location;

public interface LocationRepository extends JpaRepository<Location, String> {

	Location findByMesterId(String mesterId);

	List<Location> findAllByMesterIdIn(Iterable<String> ids);

}
