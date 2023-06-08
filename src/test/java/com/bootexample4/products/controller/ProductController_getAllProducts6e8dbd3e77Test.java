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

    private List<Product> productList;

    @BeforeEach
    public void setup() {
        Product product1 = new Product();
        Product product2 = new Product();
        productList = Arrays.asList(product1, product2);
    }

    @Test
    public void testGetAllProducts_success() {
        Mockito.when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productController.getAllProducts();

        assertEquals(productList, result, "The expected and actual product lists do not match");
        Mockito.verify(productRepository).findAll();
    }

    @Test
    public void testGetAllProducts_emptyList() {
        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList());

        List<Product> result = productController.getAllProducts();

        assertEquals(0, result.size(), "The expected and actual product list sizes do not match");
        Mockito.verify(productRepository).findAll();
    }
}