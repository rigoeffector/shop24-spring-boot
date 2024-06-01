package com.shop24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop24.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	boolean existsByEmail(String email);

}