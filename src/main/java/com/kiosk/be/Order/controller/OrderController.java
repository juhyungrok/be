package com.kiosk.be.order.controller;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiosk.be.order.requestdto.OrderRequestDTO;
import com.kiosk.be.order.responsedto.OrderResponseDTO;
import com.kiosk.be.order.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long orderId) {
        OrderResponseDTO order = orderService.getOrderById(orderId);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        // Calculate totalAmount
        BigDecimal totalAmount = orderRequest.getOrderDetails().stream()
                .map(orderDetailDTO -> BigDecimal.valueOf(orderDetailDTO.getQuantity())
                        .multiply(orderDetailDTO.getTotalPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Set totalAmount in the main order
        orderRequest.setTotalAmount(totalAmount);

        // Create Order entity
        OrderResponseDTO createdOrder = orderService.createOrder(orderRequest);

        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.delete(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
