package com.shop24.contoller;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.shop24.dto.OrderDTO;
import com.shop24.model.CargoCompany;
import com.shop24.model.Order;
import com.shop24.service.CargoCompanyService;
import com.shop24.service.OrderService;
import com.shop24.util.DTOMapper;
import com.shop24.util.ResponseBuilder;
import com.shop24.util.Shop24APIMessages;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    
    @Autowired
    private CargoCompanyService cargoCompanyService;
    
    
    @PostMapping("/client/{clientId}")
    public ResponseEntity<Object> createOrder(@PathVariable Long clientId, @RequestBody Order order) {
        try {
            // Assuming cargo company is provided in the request body or fetched from somewhere
            CargoCompany cargoCompany = cargoCompanyService.getCargoCompanyById(order.getCargoCompany().getId());
            order.setCargoCompany(cargoCompany);

            Order createdOrder = orderService.createOrder(clientId, order);
            OrderDTO orderDTO = DTOMapper.toOrderDTO(createdOrder);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseBuilder.buildResponse(Shop24APIMessages.ADD_ORDER_SUCCESS, true, orderDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Object> getOrdersByClientId(@PathVariable Long clientId) {
        try {
            List<Order> orders = orderService.getOrdersByClientId(clientId);
            List<OrderDTO> orderDTOs = orders.stream().map(DTOMapper::toOrderDTO).collect(Collectors.toList());
            return ResponseEntity.ok()
                    .body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIEVED_ALL_ORDERS, true, orderDTOs));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
        }
    }
    
    @PutMapping("/{id}/mark-as-paid")
    public ResponseEntity<Object> markOrderAsPaid(@PathVariable Long id) {
        try {
            orderService.markOrderAsPaid(id);
            return ResponseEntity.ok()
                    .body(ResponseBuilder.buildResponse("Order marked as paid successfully.", true, null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
        }
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Object> completeOrder(@PathVariable Long id) {
        try {
            orderService.completeOrder(id);
            return ResponseEntity.ok()
                    .body(ResponseBuilder.buildResponse(Shop24APIMessages.ORDER_COMPLETED_SUCCESS, true, null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
        }
    }

    
    
    @GetMapping("/top-paid/{limit}")
    public ResponseEntity<Object> getTopPaidOrders(@PathVariable int limit) {
        try {
            List<OrderDTO> topPaidOrders = orderService.getTopPaidOrders(limit);
            return ResponseEntity.ok()
                    .body(ResponseBuilder.buildResponse("Top paid orders retrieved successfully.", true, topPaidOrders));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
        }
    }
    
    
    @GetMapping("/top-five-orders-by-different-clients")
    public ResponseEntity<Object> getTopFiveOrdersByDifferentClients() {
        try {
            List<OrderDTO> orders = orderService.getTopFiveOrdersByDifferentClients();
            return ResponseEntity.ok().body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIEVED_TOP_ORDERS, true, orders));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
        }
    }
    
   

}