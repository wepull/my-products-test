package com.bootexample4.products.controller;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductController_createProduct3bb534587bTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    private Product testProduct;

    @BeforeEach
    public void setUp() {
        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setDescription("Test Product Description");
        testProduct.setPrice(100.0);
    }

    @Test
    public void testCreateProduct_success() {
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        Product createdProduct = productController.createProduct(testProduct);

        assertNotNull(createdProduct);
        assertEquals(testProduct.getId(), createdProduct.getId());
        assertEquals(testProduct.getName(), createdProduct.getName());
        assertEquals(testProduct.getDescription(), createdProduct.getDescription());
        assertEquals(testProduct.getPrice(), createdProduct.getPrice());
    }

    @Test
    public void testCreateProduct_failure() {
        when(productRepository.save(any(Product.class))).thenThrow(new RuntimeException("Error saving product"));

        assertThrows(RuntimeException.class, () -> productController.createProduct(testProduct));
    }
}