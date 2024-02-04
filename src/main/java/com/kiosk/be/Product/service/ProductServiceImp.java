package com.kiosk.be.Product.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiosk.be.Order.controller.entity.Order;
import com.kiosk.be.Order.repository.OrderRepository;
import com.kiosk.be.Product.controller.entity.Product;
import com.kiosk.be.Product.domain.ProductDto;
import com.kiosk.be.Product.repository.ProductRepository;
import java.util.stream.Collectors;

import lombok.extern.log4j.Log4j2;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    public ProductServiceImp(ProductRepository productRepository, ModelMapper modelMapper,
            OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, Product.class))
                .collect(Collectors.toList());
    }

    @Override
    public void saveOrder(Long productId, int quantity) {
        @SuppressWarnings("null")
        Product product = productRepository.findById(productId).orElse(null);

        Order order = new Order();
        order.setProduct(product);
        order.setQuantity(quantity);
        orderRepository.save(order);

    }

}
