package com.saira.sairarestful.controller;

import com.saira.sairarestful.entity.Product;
import com.saira.sairarestful.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    // Create a new product
    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }
    // Get all products
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.fetchAllProducts();
        return ResponseEntity.ok(products);
    }
    // Get a product by ID
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.fetchProductById(id);
        return productOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Update a product
    @PutMapping(path = "/product/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> updatedProductOptional = productService.updateProduct(id, product);
        return updatedProductOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    //Delte a product
    @DeleteMapping(value = "/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean deletionStatus = productService.deleteProduct(id);
        if (deletionStatus) {
            return ResponseEntity.ok("Product with ID " + id + " has been deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product with ID " + id);
        }
    }
}