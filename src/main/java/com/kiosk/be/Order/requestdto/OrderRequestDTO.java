package com.kiosk.be.order.requestdto;

import java.math.BigDecimal;
import java.util.List;

public class OrderRequestDTO {
    private List<OrderDetailDTO> orderDetails;
    private BigDecimal totalAmount;

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
