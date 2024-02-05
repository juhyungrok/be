package com.kiosk.be.order.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kiosk.be.order.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query("SELECT COALESCE(SUM(od.totalPrice), 0) FROM OrderDetail od WHERE od.order.orderId = :orderId")
    BigDecimal calculateTotalAmount(@Param("orderId") Long orderId);

}
