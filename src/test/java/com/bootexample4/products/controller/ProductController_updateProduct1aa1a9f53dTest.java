package com.bootexample4.products.controller;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ProductController_updateProduct1aa1a9f53dTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    private Product existingProduct;
    private Product updatedProduct;

    @BeforeEach
    void setUp() {
        existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Old Product");
        existingProduct.setDescription("Old description");
        existingProduct.setPrice(100);

        updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("New Product");
        updatedProduct.setDescription("New description");
        updatedProduct.setPrice(200);
    }

    @Test
    public void testUpdateProduct_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        ResponseEntity<Product> response = productController.updateProduct(1L, updatedProduct);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedProduct, response.getBody());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    public void testUpdateProduct_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.updateProduct(1L, updatedProduct);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(0)).save(existingProduct);
    }
}