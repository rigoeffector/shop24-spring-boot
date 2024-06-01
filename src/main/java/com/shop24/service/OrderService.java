package com.shop24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop24.model.Client;
import com.shop24.model.Order;
import com.shop24.repository.ClientRepository;
import com.shop24.repository.OrderRepository;
import com.shop24.util.Shop24APIMessages;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientRepository clientRepository;

    public Order createOrder(Long clientId,Order   order) {
    	Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalArgumentException(Shop24APIMessages.CLIENT_NOT_FOUND));
        order.setClient(client);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrdersByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalArgumentException(Shop24APIMessages.ORDER_NOT_FOUND));
        return orderRepository.findByClient(client);
    }

    // Add other methods for the required functionalities
}