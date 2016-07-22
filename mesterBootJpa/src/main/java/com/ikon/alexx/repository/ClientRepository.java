package com.ikon.alexx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikon.alexx.entity.Client;

public interface ClientRepository extends JpaRepository<Client, String>{

}
