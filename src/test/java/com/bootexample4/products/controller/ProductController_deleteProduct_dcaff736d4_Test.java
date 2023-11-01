// Test generated by RoostGPT for test expressscriptsdemo using AI Type Open AI and AI Model gpt-4

package com.bootexample4.products.controller;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductController_deleteProduct_dcaff736d4_Test {

    @Mock
    private ProductRepository productRepository;
    
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productController = new ProductController(productRepository);
    }

    @Test
    public void testDeleteProduct_Success() {
        Product product = new Product();
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ResponseEntity<Object> responseEntity = productController.deleteProduct(1L);

        verify(productRepository, times(1)).delete(product);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testDeleteProduct_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Object> responseEntity = productController.deleteProduct(1L);

        verify(productRepository, times(0)).delete(any());
        assertEquals(404, responseEntity.getStatusCodeValue());
    }
}
