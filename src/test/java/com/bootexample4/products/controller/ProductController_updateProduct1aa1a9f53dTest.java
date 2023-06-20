package com.bootexample4.products.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductController_createProduct3bb534587bTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("iPhone");
        product.setPrice(1000);

        when(productRepository.save(product)).thenReturn(product);

        ResponseEntity<Product> responseEntity = productController.createProduct(product);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }
}