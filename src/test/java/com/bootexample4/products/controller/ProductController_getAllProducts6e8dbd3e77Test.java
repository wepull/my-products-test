package com.bootexample4.products.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductController_getAllProducts6e8dbd3e77Test {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    private Product product1;
    private Product product2;
    private List<Product> productList;

    @BeforeEach
    public void setUp() {
        product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setDescription("Description 1");
        product1.setPrice(100.0);

        product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setDescription("Description 2");
        product2.setPrice(200.0);

        productList = Arrays.asList(product1, product2);
    }

    @Test
    public void testGetAllProducts_success() {
        Mockito.when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productController.getAllProducts();

        assertEquals(productList, result, "Expected and actual product lists should match");
    }

    @Test
    public void testGetAllProducts_emptyList() {
        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList());

        List<Product> result = productController.getAllProducts();

        assertEquals(0, result.size(), "Expected empty list when no products are available");
    }
}