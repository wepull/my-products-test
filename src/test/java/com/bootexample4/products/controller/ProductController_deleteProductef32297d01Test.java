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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductController_deleteProductef32297d01Test {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    private Product testProduct;

    @BeforeEach
    public void setUp() {
        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setPrice(100.0);
    }

    @Test
    public void testDeleteProduct_success() {
        when(productRepository.findById(testProduct.getId())).thenReturn(Optional.of(testProduct));
        doNothing().when(productRepository).delete(testProduct);

        ResponseEntity<Object> response = productController.deleteProduct(testProduct.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productRepository, times(1)).findById(testProduct.getId());
        verify(productRepository, times(1)).delete(testProduct);
    }

    @Test
    public void testDeleteProduct_notFound() {
        when(productRepository.findById(testProduct.getId())).thenReturn(Optional.empty());

        ResponseEntity<Object> response = productController.deleteProduct(testProduct.getId());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productRepository, times(1)).findById(testProduct.getId());
        verify(productRepository, times(0)).delete(testProduct);
    }
}