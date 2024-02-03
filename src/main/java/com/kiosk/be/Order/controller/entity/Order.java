package com.kiosk.be.Order.controller.entity;

import java.util.ArrayList;

import org.hibernate.mapping.Array;
import org.hibernate.mapping.List;

import com.kiosk.be.Product.controller.entity.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    private int quantity;
    private int totalprice;

    public Long getOrderId() {
        return orderId;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Product> products = new ArrayList<>();
}
