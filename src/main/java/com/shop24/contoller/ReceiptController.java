//package com.shop24.contoller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.shop24.model.Receipt;
//import com.shop24.service.ReceiptService;
//
//@RestController
//@RequestMapping("/api/receipts")
//public class ReceiptController {
//
//    @Autowired
//    private ReceiptService receiptService;
//
//    @GetMapping("/generate/{orderId}")
//    public Receipt generateReceipt(@PathVariable Long orderId) {
//        return receiptService.generateReceipt(orderId);
//    }
//}
