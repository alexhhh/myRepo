package com.ikon.alexx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikon.alexx.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

}
