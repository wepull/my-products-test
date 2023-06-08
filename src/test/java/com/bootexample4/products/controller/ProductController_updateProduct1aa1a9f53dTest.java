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
import static org.mockito.ArgumentMatchers.any;
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
    public void testUpdateProductSuccess() {
        when(productRepository.findById(existingProduct.getId())).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        ResponseEntity<Product> response = productController.updateProduct(existingProduct.getId(), updatedProduct);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());
    }

    @Test
    public void testUpdateProductNotFound() {
        when(productRepository.findById(existingProduct.getId())).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.updateProduct(existingProduct.getId(), updatedProduct);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}