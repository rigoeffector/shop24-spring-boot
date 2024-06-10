//package com.shop24.service;
//
//import com.shop24.model.Order;
//import com.shop24.model.Receipt;
//import com.shop24.repository.ReceiptRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class ReceiptService {
//
//    @Autowired
//    private ReceiptRepository receiptRepository;
//
//    @Autowired
//    private OrderService orderService;
//
//    public Receipt generateReceipt(Long orderId) {
//        // Check if a receipt already exists for the given order ID
//        if (receiptRepository.existsByOrderId(orderId)) {
//            throw new IllegalArgumentException("Receipt already exists for order with ID: " + orderId);
//        }
//
//        // Retrieve the order
//        Order order = orderService.getOrderById(orderId);
//        if (order == null) {
//            throw new IllegalArgumentException("Order not found with ID: " + orderId);
//        }
//
//        // Calculate total amount
//        double totalAmount = calculateTotalAmount(order);
//
//        // Create a new receipt
//        Receipt receipt = new Receipt();
//        receipt.setOrder(order);
//        receipt.setIssuedAt(LocalDateTime.now());
//        receipt.setTotalAmount(totalAmount);
//
//        // Save the receipt
//        return receiptRepository.save(receipt);
//    }
//
//    private double calculateTotalAmount(Order order) {
//        // Implement your logic to calculate the total amount based on the order details
//        // For example, you can iterate through the order items and calculate the total amount
//        // This is just a placeholder method
//        return 0.0;
//    }
//}
