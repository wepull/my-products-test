package com.bootexample4.products.controller;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductController_createProduct3bb534587bTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateProduct_success() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product1");
        product.setDescription("Product1 description");
        product.setPrice(100.0);

        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productController.createProduct(product);

        assertEquals(product.getId(), createdProduct.getId());
        assertEquals(product.getName(), createdProduct.getName());
        assertEquals(product.getDescription(), createdProduct.getDescription());
        assertEquals(product.getPrice(), createdProduct.getPrice());

        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testCreateProduct_nullProduct() {
        Product product = null;

        when(productRepository.save(product)).thenReturn(null);

        Product createdProduct = productController.createProduct(product);

        assertEquals(null, createdProduct);

        verify(productRepository, times(1)).save(product);
    }
}