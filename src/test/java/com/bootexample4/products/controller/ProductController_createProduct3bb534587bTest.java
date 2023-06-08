package com.bootexample4.products.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductController_createProduct3bb534587bTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Product Description");
        product.setPrice(100.0);
    }

    @Test
    public void testCreateProductSuccess() {
        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productController.createProduct(product);

        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testCreateProductFailure() {
        when(productRepository.save(null)).thenThrow(new IllegalArgumentException("Product must not be null"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productController.createProduct(null));

        assertEquals("Product must not be null", exception.getMessage());
        verify(productRepository, times(1)).save(null);
    }
}