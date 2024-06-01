package com.shop24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop24.model.CargoCompany;

@Repository
public interface CargoCompanyRepository extends JpaRepository<CargoCompany, Long> {
}