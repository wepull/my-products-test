package com.bootexample4.products.controller;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductController_updateProduct1aa1a9f53dTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    private Product existingProduct;
    private Product updatedProduct;

    @BeforeEach
    public void setUp() {
        existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Old Product");
        existingProduct.setDescription("Old Description");
        existingProduct.setPrice(100.0);

        updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("New Product");
        updatedProduct.setDescription("New Description");
        updatedProduct.setPrice(200.0);
    }

    @Test
    public void testUpdateProduct_success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        ResponseEntity<Product> response = productController.updateProduct(1L, updatedProduct);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedProduct, response.getBody());
    }

    @Test
    public void testUpdateProduct_notFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.updateProduct(1L, updatedProduct);

        assertEquals(404, response.getStatusCodeValue());
    }
}