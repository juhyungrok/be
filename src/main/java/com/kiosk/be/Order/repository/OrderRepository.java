package com.kiosk.be.Order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.be.Order.controller.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
