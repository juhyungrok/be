package com.kiosk.be.Order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiosk.be.Product.service.ProductService;

@RestController

public class OrderController {

    @Autowired
    private ProductService productService;

    @PostMapping("/api/orders/create")
    public ResponseEntity<String> createOrder(@RequestParam("productId") Long productId,
            @RequestParam("quantity") int quantity) {
        productService.saveOrder(productId, quantity);

        return ResponseEntity.ok("안녕 하세요");
    }

}
