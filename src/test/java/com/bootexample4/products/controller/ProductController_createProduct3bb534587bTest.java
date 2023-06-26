package com.bootexample4.products.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductController_createProduct3bb534587bTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testCreateProduct_whenProductIsValid_thenReturnProduct() throws Exception {
        // Given
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(100);

        // When
        Mockito.when(productRepository.save(product)).thenReturn(product);

        // Then
        ResponseEntity<Product> response = mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType("application/json")
                .content(asJsonString(product)))
                .andReturn().getResponse().getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testCreateProduct_whenProductIsInvalid_thenReturnBadRequest() throws Exception {
        // Given
        Product product = new Product();
        product.setName("");
        product.setPrice(100);

        // When
        Mockito.when(productRepository.save(product)).thenReturn(null);

        // Then
        ResponseEntity<Product> response = mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType("application/json")
                .content(asJsonString(product)))
                .andReturn().getResponse().getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}