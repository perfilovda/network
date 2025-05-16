package com.example.api_gateway.controller;

import com.example.api_gateway.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class GatewayController {
    @Autowired
    private RestTemplate restTemplate;

    private static final String CORE_SERVICE_URL = "http://localhost:8081";

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        return restTemplate.getForEntity(CORE_SERVICE_URL + "/products", Object.class);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return restTemplate.getForEntity(CORE_SERVICE_URL + "/products/" + id, Object.class);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return restTemplate.postForEntity(CORE_SERVICE_URL + "/products", product, Product.class);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return restTemplate.exchange(CORE_SERVICE_URL + "/products/" + id, HttpMethod.PUT, new HttpEntity<>(product), Product.class);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return restTemplate.exchange(CORE_SERVICE_URL + "/products/" + id, HttpMethod.DELETE, null, Object.class);
    }
}