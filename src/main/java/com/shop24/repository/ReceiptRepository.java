package com.shop24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop24.model.Receipt;

import java.util.List;
import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    Optional<Receipt> existsByOrderId(Long orderId);

    @Query("SELECT r.id AS receiptId, r.issuedAt AS issuedAt, r.totalAmount AS totalAmount, " +
            "o.id AS orderId, c.id AS clientId, c.name AS clientName, " +
            "cc.id AS cargoCompanyId, cc.name AS cargoCompanyName, " +
            "d.id AS drinkId, d.name AS drinkName, d.description AS drinkDescription, " +
            "d.price AS drinkPrice, d.quantity AS drinkQuantity " +
            "FROM Receipt r " +
            "JOIN r.order o " +
            "JOIN o.client c " +
            "JOIN o.cargoCompany cc " +
            "JOIN o.drinks d " +
            "WHERE o.id = :orderId")
    List<Object[]> getReceiptDetailsByOrderId(Long orderId);

	
}
