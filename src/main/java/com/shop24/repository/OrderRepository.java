package com.shop24.repository;

import org.springframework.data.domain.Pageable;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop24.model.Client;
import com.shop24.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
    List<Order> findByClient(Client client);
    
    @Query(value = "SELECT o FROM Order o JOIN FETCH o.cargoCompany WHERE o.isPaid = true ORDER BY o.id DESC")
    List<Order> findTopPaidOrders(Pageable pageable);
    
    
    @Query(value = "SELECT DISTINCT o FROM Order o ORDER BY o.client.id")
    List<Order> findTopOrdersByDifferentClients(Pageable pageable);
}
