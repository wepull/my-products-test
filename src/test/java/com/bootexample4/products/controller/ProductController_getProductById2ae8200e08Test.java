package com.bootexample4.products.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductController_getAllProducts6e8dbd3e77Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getAllProducts_whenProductsExist_thenReturnProducts() throws Exception {
        // Given
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setPrice(100.0);
        products.add(product1);
        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(200.0);
        products.add(product2);

        when(productRepository.findAll()).thenReturn(products);

        // When
        ResponseEntity<List<Product>> response = mockMvc.perform(get("/products"))
                .andReturn()
                .getResponse()
                .getBody();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    public void getAllProducts_whenNoProductsExist_thenReturnEmptyList() throws Exception {
        // Given
        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<List<Product>> response = mockMvc.perform(get("/products"))
                .andReturn()
                .getResponse()
                .getBody();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new ArrayList<>(), response.getBody());
    }
}