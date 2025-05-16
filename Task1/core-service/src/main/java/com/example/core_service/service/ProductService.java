package com.example.core_service.service;

import com.example.core_service.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Product createProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = getProductById(id);
        if (existingProduct.isPresent()) {
            Product p = existingProduct.get();
            p.setName(updatedProduct.getName());
            p.setQuantity(updatedProduct.getQuantity());
            return Optional.of(p);
        }
        return Optional.empty();
    }

    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }
}