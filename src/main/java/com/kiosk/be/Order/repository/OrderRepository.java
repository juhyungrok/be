package com.kiosk.be.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.be.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
