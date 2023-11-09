// Test generated by RoostGPT for test tcsdemonov9 using AI Type Open AI and AI Model gpt-4

package com.bootexample4.products.controller;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductController_updateProduct_9454a9af90_Test {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateProduct_Success() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product1");
        product.setDescription("Description1");
        product.setPrice(100.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ResponseEntity<Product> response = productController.updateProduct(1L, product);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testUpdateProduct_NotFound() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product1");
        product.setDescription("Description1");
        product.setPrice(100.0);

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.updateProduct(1L, product);
        assertEquals(404, response.getStatusCodeValue());
    }
}
