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
public class ProductController_updateProduct1aa1a9f53dTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    private Product existingProduct;
    private Product updatedProduct;

    @BeforeEach
    public void setUp() {
        existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Product1");
        existingProduct.setDescription("Description1");
        existingProduct.setPrice(100.0);

        updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product1");
        updatedProduct.setDescription("Updated Description1");
        updatedProduct.setPrice(150.0);
    }

    @Test
    public void testUpdateProduct_success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        ResponseEntity<Product> response = productController.updateProduct(1L, updatedProduct);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct.getName(), response.getBody().getName());
        assertEquals(updatedProduct.getDescription(), response.getBody().getDescription());
        assertEquals(updatedProduct.getPrice(), response.getBody().getPrice());
    }

    @Test
    public void testUpdateProduct_notFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.updateProduct(1L, updatedProduct);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}