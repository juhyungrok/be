package com.kiosk.be.Order.repository;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.be.Order.controller.entity.Order;
import com.kiosk.be.Order.service.OrderService;
import com.kiosk.be.Product.controller.entity.Product;

public interface OrderRepository extends JpaRepository<Order, Long> {

    default void saveOrder(OrderService orderService, Long productId, int quantity) {
        // id로 검색
        @SuppressWarnings("null")
        Product product = orderService.productRepository.findById(productId).orElse(null);

        if (product != null) {
            // 총금액
            int totalprice = product.getPrice() * quantity;

            // 주문 만들기

            Order order = new Order();
            order.setTotalprice(totalprice);
            order.setLocalDateTime(LocalDateTime.now());

            // 주문서 작성하기
            product.setOrder(order);

            // 제품에 대한 주문서
            order.setProducts(Collections.singletonList(product));

            save(order);
            orderService.productRepository.save(product);
        }
    }

}
