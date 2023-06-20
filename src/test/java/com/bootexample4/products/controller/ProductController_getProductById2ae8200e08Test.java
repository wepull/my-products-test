package com.bootexample4.products.controller;

import com.bootexample4.products.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ProductController.class)
public class ProductController_getProductById2ae8200e08Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getProductById_whenProductExists_thenReturnProduct() throws Exception {
        // Given
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(100.0);

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));

        // When
        ResponseEntity<Product> response = mockMvc.perform(get("/products/1"))
                .andReturn().getResponse().getBody();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void getProductById_whenProductDoesNotExist_thenReturnNotFound() throws Exception {
        // Given
        when(productRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // When
        ResponseEntity<Product> response = mockMvc.perform(get("/products/1"))
                .andReturn().getResponse().getBody();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}