package com.kiosk.be.Order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.be.Order.repository.OrderRepository;
import com.kiosk.be.Product.controller.entity.Product;
import com.kiosk.be.Product.repository.ProductRepository;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(Long productId) {
        // Id커피 아이디 검색
        Product product = productRepository.findById(productId).orElse(null);

        if (product != null) {
            // 총금액

            double totalprice = product.getPrice() * product.getQuantity();
                잠만여기서 멈춰  gpt가 이상함
        }
    }

}
