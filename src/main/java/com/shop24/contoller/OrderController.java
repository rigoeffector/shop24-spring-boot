package com.shop24.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop24.model.Order;
import com.shop24.service.OrderService;
import com.shop24.util.ResponseBuilder;
import com.shop24.util.Shop24APIMessages;
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/client/{clientId}")
    public ResponseEntity<Object> createOrder(@PathVariable Long clientId, @RequestBody Order order) {
        try {
            Order createdOrder = orderService.createOrder(clientId, order);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseBuilder.buildResponse(Shop24APIMessages.ADD_ORDER_SUCCESS, true, createdOrder));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Object> getOrdersByClientId(@PathVariable Long clientId) {
        try {
            List<Order> orders = orderService.getOrdersByClientId(clientId);
            return ResponseEntity.ok().body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIEVED_ORDER__WITH_ID, true, orders));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
        }
    }
}