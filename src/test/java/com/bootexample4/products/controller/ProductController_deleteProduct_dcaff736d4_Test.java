package com.bootexample4.products.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductController_deleteProduct_dcaff736d4_Test {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @Before
    public void setup() {
        product = new Product();
        product.setId(1L);
        product.setName("Product Name");
        product.setDescription("Product Description");
        product.setPrice(100.00);
    }

    @Test
    public void testDeleteProduct_success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(product);
        ResponseEntity<Object> response = productController.deleteProduct(1L);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteProduct_notFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Object> response = productController.deleteProduct(1L);
        assertEquals(404, response.getStatusCodeValue());
    }
}
