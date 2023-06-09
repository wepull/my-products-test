package com.bootexample4.products.controller;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductController_getProductById2ae8200e08Test {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductRepository productRepository;

    Product product1;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setDescription("Product 1 Description");
        product1.setPrice(100.0);
    }

    @Test
    public void testGetProductById_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        ResponseEntity<Product> response = productController.getProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product1, response.getBody());
    }

    @Test
    public void testGetProductById_NotFound() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.getProductById(2L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}