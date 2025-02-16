package com.saira.sairarestful.service;

import com.saira.sairarestful.entity.Product;
import com.saira.sairarestful.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product saveProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to save product: " + e.getMessage());
        }
    }
    // Get all products
    public List<Product> fetchAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to fetch all products: " + e.getMessage());
        }
    }
    // Get a product by ID
    public Optional<Product> fetchProductById(Long id) {
        try {
            return productRepository.findById(id);
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to fetch product by ID: " + e.getMessage());
        }
    }
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        try {
            Optional<Product> existingProductOptional = productRepository.findById(id);
            if (existingProductOptional.isPresent()) {
                Product existingProduct = existingProductOptional.get();
                existingProduct.setName(updatedProduct.getName());
                existingProduct.setPrice(updatedProduct.getPrice());
                existingProduct.setQuantity(updatedProduct.getQuantity());
                Product savedEntity = productRepository.save(existingProduct);
                return Optional.of(savedEntity);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to update product: " + e.getMessage());
        }
    }
    public boolean deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return true; // Deletion successful
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to delete product: " + e.getMessage());
        }
    }
}
