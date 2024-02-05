package com.kiosk.be.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.be.product.entity.Product;
import com.kiosk.be.product.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @SuppressWarnings("null")
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @SuppressWarnings("null")
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = getProductById(productId);

        if (existingProduct != null) {
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setImageUrl(updatedProduct.getImageUrl());

            return productRepository.save(existingProduct);
        }

        return null; // Handle not found scenario
    }

    @SuppressWarnings("null")
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);

    }

}
