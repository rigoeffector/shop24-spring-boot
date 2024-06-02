package com.shop24.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shop24.dto.OrderDTO;
import com.shop24.model.CargoCompany;
import com.shop24.model.Client;
import com.shop24.model.Order;
import com.shop24.repository.ClientRepository;
import com.shop24.repository.OrderRepository;
import com.shop24.util.DTOMapper;
import com.shop24.util.Shop24APIMessages;
import org.springframework.data.domain.Pageable;



@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private CargoCompanyService cargoCompanyService;

    public Order createOrder(Long clientId, Order order) {
        // Retrieve the client
        Client client = clientRepository.findById(clientId)
                                        .orElseThrow(() -> new IllegalArgumentException(Shop24APIMessages.CLIENT_NOT_FOUND));

        // Set the client in the order
        order.setClient(client);

        // Assuming cargo company is provided in the request body or fetched from somewhere
        CargoCompany cargoCompany = order.getCargoCompany();
        if (cargoCompany != null) {
            // Fetch the cargo company by its ID if it's not null
            cargoCompany = cargoCompanyService.getCargoCompanyById(cargoCompany.getId());
            // Set the fetched cargo company in the order
            order.setCargoCompany(cargoCompany);
        }

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

    public Order completeOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found."));
        if (order.isCompleted()) {
            throw new IllegalArgumentException("Order is already completed.");
        }
        if (!order.isPaid()) {
            throw new IllegalArgumentException("Order must be paid before it can be completed.");
        }
        order.setCompleted(true);
        return orderRepository.save(order);
    }
    public void markOrderAsPaid(Long orderId) {
        Order order = getOrderById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        if (order.isPaid()) {
            throw new IllegalArgumentException("Order is already paid.");
        }
        order.setPaid(true);
        orderRepository.save(order);
        // Automatically complete the order after marking it as paid
        completeOrder(orderId);
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
    
    public List<Order> findTopOrdersByDifferentClients(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return orderRepository.findTopOrdersByDifferentClients(pageable);
    }

}