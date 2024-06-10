package com.shop24.repository;

import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop24.model.Client;
import com.shop24.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
    List<Order> findByClient(Client client);
    
    @Query(value = "SELECT o FROM Order o JOIN FETCH o.cargoCompany WHERE o.isPaid = true ORDER BY o.id DESC")
    List<Order> findTopPaidOrders(Pageable pageable);
    
    
    @Query(value = "SELECT o FROM Order o WHERE o.client.id IN (SELECT DISTINCT client.id FROM Order) ORDER BY o.id DESC")
    List<Order> findTopOrdersByDifferentClients(Pageable pageable);
    
    @Query("SELECT d.name, SUM(d.quantity) AS totalQuantity " +
            "FROM Order o JOIN o.drinks d " +
            "GROUP BY d.name " +
            "ORDER BY totalQuantity DESC")
     List<Object[]> findMostConsumedDrinks();
     
     @Query("SELECT o FROM Order o JOIN FETCH o.cargoCompany WHERE o.id = :orderId")
     Optional<Order> findByIdWithCargoCompany(@Param("orderId") Long orderId);
}
