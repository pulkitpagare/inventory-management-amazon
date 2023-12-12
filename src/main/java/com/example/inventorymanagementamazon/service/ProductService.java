package com.example.inventorymanagementamazon.service;

import com.example.inventorymanagementamazon.annotation.LogExecutionTime;
import com.example.inventorymanagementamazon.dto.ProductDTO;
import com.example.inventorymanagementamazon.entity.Category;
import com.example.inventorymanagementamazon.entity.Product;
import com.example.inventorymanagementamazon.exception.CategoryRequiredException;
import com.example.inventorymanagementamazon.exception.NotFoundException;
import com.example.inventorymanagementamazon.repository.CategoryRepository;
import com.example.inventorymanagementamazon.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    @LogExecutionTime
    public Product addProduct(ProductDTO productDTO) throws CategoryRequiredException {
        List<Category> categories = categoryRepository.findAllById(productDTO.getCategories());
        if (categories.isEmpty()){
            throw new CategoryRequiredException("At least one Category is required.");
        }
        Product product = Product.builder()
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .name(productDTO.getName())
                .categories(new HashSet<>(categories))
                .brandId(productDTO.getBrandId())
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();
        return productRepository.save(product);
    }

    @LogExecutionTime
    public void deleteProduct(BigInteger id){

        Optional<Product> product = productRepository.findById(id);
        product.ifPresentOrElse(productEntity -> {
            productEntity.setCategories(Collections.emptySet());
            productRepository.save(productEntity);
            productRepository.delete(productEntity);
        },
                () -> {throw new NotFoundException("Product Does not exist.");
        });
    }
}
