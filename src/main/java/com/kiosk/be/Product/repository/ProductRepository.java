package com.kiosk.be.Product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.be.Product.controller.entity.Product;
import com.kiosk.be.Product.domain.ProductDto;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
