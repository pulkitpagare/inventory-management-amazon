package com.example.inventorymanagementamazon.controller;

import com.example.inventorymanagementamazon.dto.ProductDTO;
import com.example.inventorymanagementamazon.entity.Product;
import com.example.inventorymanagementamazon.exception.CategoryRequiredException;
import com.example.inventorymanagementamazon.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private  ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> allProducts = productService.getAllProducts();
        return ResponseEntity.ok().body(allProducts);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductDTO productDTO) throws CategoryRequiredException {
        Product product = productService.addProduct(productDTO);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") BigInteger id){
        productService.deleteProduct(id);
    }
}
