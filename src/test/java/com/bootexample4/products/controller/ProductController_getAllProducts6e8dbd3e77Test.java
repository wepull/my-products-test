package com.bootexample4.products.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductController_getAllProducts6e8dbd3e77Test {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    private List<Product> productList;

    @BeforeEach
    public void setUp() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setDescription("Description 1");
        product1.setPrice(10.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setDescription("Description 2");
        product2.setPrice(20.0);

        Product product3 = new Product();
        product3.setId(3L);
        product3.setName("Product 3");
        product3.setDescription("Description 3");
        product3.setPrice(30.0);

        productList = Arrays.asList(product1, product2, product3);
    }

    @Test
    public void testGetAllProducts_success() {
        // Arrange
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<Product> result = productController.getAllProducts();

        // Assert
        assertEquals(productList, result);
    }

    @Test
    public void testGetAllProducts_emptyList() {
        // Arrange
        when(productRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Product> result = productController.getAllProducts();

        // Assert
        assertEquals(0, result.size());
    }
}