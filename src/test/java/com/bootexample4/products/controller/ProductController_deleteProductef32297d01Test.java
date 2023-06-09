package com.bootexample4.products.controller;

import java.util.Optional;
import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductController_deleteProductef32297d01Test {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    private Product product;
    private Long productId;

    @BeforeEach
    public void setUp() {
        productId = 1L;
        product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        product.setDescription("Test Product Description");
        product.setPrice(100.0);
    }

    @Test
    public void testDeleteProduct_success() {
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        Mockito.doNothing().when(productRepository).delete(product);

        ResponseEntity<Object> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(productRepository, Mockito.times(1)).findById(productId);
        Mockito.verify(productRepository, Mockito.times(1)).delete(product);
    }

    @Test
    public void testDeleteProduct_notFound() {
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Mockito.verify(productRepository, Mockito.times(1)).findById(productId);
        Mockito.verify(productRepository, Mockito.times(0)).delete(product);
    }
}