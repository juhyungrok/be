package com.kiosk.be.Product.service;

import java.util.List;

import com.kiosk.be.Product.controller.entity.Product;

public interface ProductService {

    List<Product> getAllProducts();

    void saveOrder(Long productId, int quantity);

}
