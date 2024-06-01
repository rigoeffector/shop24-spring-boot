package com.shop24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop24.model.Drink;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
     
	 boolean existsByName(String name);
}