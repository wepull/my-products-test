package com.bootexample4.products.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

public class ProductController_getProductById_d22f3ea272_Test {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductById_Success() {
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("Product A");
        mockProduct.setDescription("Description A");
        mockProduct.setPrice(100.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        ResponseEntity<Product> responseEntity = productController.getProductById(1L);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockProduct, responseEntity.getBody());
    }

    @Test
    public void testGetProductById_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Product> responseEntity = productController.getProductById(1L);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }
}
