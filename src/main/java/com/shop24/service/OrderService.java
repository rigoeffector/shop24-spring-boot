package com.shop24.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shop24.dto.OrderDTO;
import com.shop24.model.Client;
import com.shop24.model.Drink;
import com.shop24.model.Order;
import com.shop24.repository.ClientRepository;
import com.shop24.repository.DrinkRepository;
import com.shop24.repository.OrderRepository;
import com.shop24.repository.ReceiptRepository;
import com.shop24.util.DTOMapper;
import com.shop24.util.Shop24APIMessages;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Pageable;



@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DrinkRepository drinkRepository;
    
    @Autowired
    private ReceiptRepository receiptRepository;
    
    @Transactional
    public Order createOrder(Long clientId, Order order) {
        // Retrieve the client
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException(Shop24APIMessages.CLIENT_NOT_FOUND));
        
        // Validate and populate drinks
        List<Drink> incomingOrderDrinks = new ArrayList<>();
        for (Drink drink : order.getDrinks()) {
            Drink fullDrinkDetails = drinkRepository.findById(drink.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Drink with ID " + drink.getId() + " not found"));
            
            // Check if the requested quantity is available
            if (fullDrinkDetails.getQuantity() < drink.getQuantity()) {
                throw new IllegalArgumentException("Not enough quantity for drink with ID " + drink.getId());
            }
            
            // Update the quantity in the order
            fullDrinkDetails.setQuantity(drink.getQuantity());
            incomingOrderDrinks.add(fullDrinkDetails);
        }
        order.setDrinks(incomingOrderDrinks);
        
        // Check if an order with the same drinks already exists for the client
        List<Order> existingOrders = orderRepository.findByClient(client);
        for (Order existingOrder : existingOrders) {
            List<Drink> existingDrinks = existingOrder.getDrinks();
            List<Drink> newOrderDrinks = order.getDrinks();
            boolean containsSameDrinks = existingDrinks.stream()
                                    .map(Drink::getId)
                                    .collect(Collectors.toSet())
                                    .equals(newOrderDrinks.stream()
                                            .map(Drink::getId)
                                            .collect(Collectors.toSet()));
            if (containsSameDrinks) {
                throw new IllegalArgumentException("Order with the same drinks already exists for " + client.getName());
            }
        }
        
        // Set the client in the order
        order.setClient(client);
        
    
        
        // Save the order
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

    @Transactional
    public Order completeOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found."));
        if (order.isCompleted()) {
            throw new IllegalArgumentException("Order is already completed.");
        }
        if (!order.isPaid()) {
            throw new IllegalArgumentException("Order must be paid before it can be completed.");
        }
        order.setCompleted(true);

        // Reduce the drink quantities
        for (Drink drink : order.getDrinks()) {
            Drink availableDrink = drinkRepository.findById(drink.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Drink not found"));
            if (availableDrink.getQuantity() < drink.getQuantity()) {
                throw new IllegalArgumentException("Not enough quantity for drink " + availableDrink.getName());
            }
            availableDrink.setQuantity(availableDrink.getQuantity() - drink.getQuantity());
            drinkRepository.save(availableDrink);
        }

        return orderRepository.save(order);
    }

    @Transactional
    public void markOrderAsPaid(Long orderId) {
        // Retrieve the order
        Order order = getOrderById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        if (order.isPaid()) {
            throw new IllegalArgumentException("Order is already paid.");
        }

        // Update drink quantities
        for (Drink drink : order.getDrinks()) {
            Drink existingDrink = drinkRepository.findById(drink.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Drink with ID " + drink.getId() + " not found"));
            
            int newQuantity = existingDrink.getQuantity() - drink.getQuantity();
            if (newQuantity < 0) {
                throw new IllegalArgumentException("Not enough quantity for drink with ID " + drink.getId());
            }
            
            existingDrink.setQuantity(newQuantity);
            drinkRepository.save(existingDrink);
        }

        // Mark the order as paid
        order.setPaid(true);
        orderRepository.save(order);

        // Automatically complete the order after marking it as paid
//        completeOrder(orderId);
    }


    public List<OrderDTO> getTopPaidOrders(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Order> topPaidOrders = orderRepository.findTopPaidOrders(pageable);
        return topPaidOrders.stream()
                .map(order -> {
                    OrderDTO orderDTO = DTOMapper.toOrderDTO(order);
                    // Convert CargoCompany to CargoCompanyDTO
                    orderDTO.setCargoCompany(DTOMapper.toCargoCompanyDTO(order.getCargoCompany()));
                    return orderDTO;
                })
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getTopFiveOrdersByDifferentClients(int limit) {
        List<Order> orders = orderRepository.findTopOrdersByDifferentClients(PageRequest.of(0, limit));
        return orders.stream().map(DTOMapper::toOrderDTO).collect(Collectors.toList());
    }
}
