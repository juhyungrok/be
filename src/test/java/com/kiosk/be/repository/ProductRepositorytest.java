package com.kiosk.be.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kiosk.be.Product.domain.ProductDto;
import com.kiosk.be.Product.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductRepositorytest {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 
     */
    @Test
    public void 제품db가져오기() {
        log.info("-----");
        log.info(productRepository);
    }

}
