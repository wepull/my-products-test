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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductController_getProductById2ae8200e08Test {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Product Description");
        product.setPrice(100.0);
    }

    @Test
    public void testGetProductById_success() {
        // TODO: Change the value of 1L to the desired product ID
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.getProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testGetProductById_notFound() {
        // TODO: Change the value of 2L to the desired product ID
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.getProductById(2L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}