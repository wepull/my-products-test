package com.bootexample4.products.controller;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

public class ProductController_getAllProducts6e8dbd3e77Test {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts_success() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> expectedProducts = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> actualProducts = productController.getAllProducts();

        assertEquals(expectedProducts, actualProducts);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllProducts_emptyList() {
        List<Product> expectedProducts = Arrays.asList();

        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> actualProducts = productController.getAllProducts();

        assertEquals(expectedProducts, actualProducts);
        verify(productRepository, times(1)).findAll();
    }
}