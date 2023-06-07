package com.bootexample4.products.controller;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductController_deleteProductef32297d01Test {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteProduct_success() {
        // TODO: Change the value of the `id` variable as needed.
        Long id = 1L;
        Product product = new Product();
        product.setId(id);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        ResponseEntity<Object> response = productController.deleteProduct(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteProduct_notFound() {
        // TODO: Change the value of the `id` variable as needed.
        Long id = 2L;

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = productController.deleteProduct(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}