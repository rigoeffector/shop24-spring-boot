package com.shop24.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop24.model.Client;
import com.shop24.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
List<Order> findByClient(Client client);
}
