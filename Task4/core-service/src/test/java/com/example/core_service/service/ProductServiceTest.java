package com.example.core_service.service;

import com.example.core_service.model.Product;
import com.example.core_service.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product(1L, "Product1", 10);
        Product product2 = new Product(2L, "Product2", 20);
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
        assertEquals("Product1", products.get(0).getName());
        assertEquals("Product2", products.get(1).getName());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Product1", 10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(1L);
        assertTrue(foundProduct.isPresent());
        assertEquals("Product1", foundProduct.get().getName());
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product(null, "New Product", 5);
        Product savedProduct = new Product(1L, "New Product", 5);
        when(productRepository.save(product)).thenReturn(savedProduct);

        Product createdProduct = productService.createProduct(product);
        assertNotNull(createdProduct.getId());
        assertEquals("New Product", createdProduct.getName());
    }
}