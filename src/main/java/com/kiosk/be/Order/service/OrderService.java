package com.kiosk.be.Order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.be.Order.repository.OrderRepository;
import com.kiosk.be.Product.repository.ProductRepository;

@Service
public class OrderService {

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public OrderRepository orderRepository;
}
