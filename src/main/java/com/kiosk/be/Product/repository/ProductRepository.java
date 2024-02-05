package com.kiosk.be.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.be.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
