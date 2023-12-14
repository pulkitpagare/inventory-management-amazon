package com.example.inventorymanagementamazon.service;

import com.example.inventorymanagementamazon.annotation.LogExecutionTime;
import com.example.inventorymanagementamazon.dto.ProductDTO;
import com.example.inventorymanagementamazon.dto.ProductSearchCriteria;
import com.example.inventorymanagementamazon.dto.ProductSearchRequest;
import com.example.inventorymanagementamazon.entity.Brand;
import com.example.inventorymanagementamazon.entity.Category;
import com.example.inventorymanagementamazon.entity.Product;
import com.example.inventorymanagementamazon.exception.CategoryRequiredException;
import com.example.inventorymanagementamazon.exception.NotFoundException;
import com.example.inventorymanagementamazon.repository.BrandRepository;
import com.example.inventorymanagementamazon.repository.CategoryRepository;
import com.example.inventorymanagementamazon.repository.ProductRepository;
import com.example.inventorymanagementamazon.specifications.ProductSearchSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    @LogExecutionTime
    public Product addProduct(ProductDTO productDTO) throws CategoryRequiredException {
        List<Category> categories = categoryRepository.findAllById(productDTO.getCategories());
        if (categories.isEmpty()){
            throw new CategoryRequiredException("At least one Category is required.");
        }
        Optional<Brand> brandOptional = brandRepository.findOne(Example.of(Brand.builder().name(productDTO.getBrandName()).build()));
        Brand brand = brandOptional.orElseThrow(() -> new NotFoundException("Brand does not exist"));
        Product product = Product.builder()
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .name(productDTO.getName())
                .categories(new HashSet<>(categories))
                .brand(brand)
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();
        return productRepository.save(product);
    }

    @LogExecutionTime
    public void deleteProduct(BigInteger id){
        log.info("finding product by id : {}", id);

        Optional<Product> product = productRepository.findById(id);

        log.info("product found : {}", product);

        product.ifPresentOrElse(productEntity -> {
            log.info("removing attached categories if any ");
            productEntity.setCategories(Collections.emptySet());
            productRepository.save(productEntity);
            log.info("deleting the product");
            productRepository.delete(productEntity);

        }, () -> {
            log.error("product with this id : {} does not exist ", id);
            throw new NotFoundException("Product Does not exist.");
        });
    }

    @LogExecutionTime
    public List<Product> searchProduct(ProductSearchRequest productSearchRequest) {
        ProductSearchCriteria.ProductSearchCriteriaBuilder productSearchCriteriaBuilder = ProductSearchCriteria.builder();
        Optional<Brand> brand = brandRepository.findOne(Example.of(Brand.builder()
                .name(productSearchRequest.getBrandName())
                .build()));
        log.info("if brand exist then add it to the productSearchCriteriaBuilder");

        brand.ifPresent(value -> productSearchCriteriaBuilder.brandId(value.getId()));

        log.info("if category exist then add it to the productSearchCriteriaBuilder");

        Optional<Category> category = categoryRepository.findOne(Example.of(Category.builder()
                .name(productSearchRequest.getCategoryName())
                .build()));
        category.ifPresent(value ->
                productSearchCriteriaBuilder.ids(value.getProducts()
                        .stream()
                        .map(Product::getId)
                        .collect(Collectors.toList())));

        log.info("if productName exist then add it to the productSearchCriteriaBuilder");

        if (Objects.nonNull(productSearchRequest.getProductName())){
            productSearchCriteriaBuilder.name(productSearchRequest.getProductName());
        }
        ProductSearchSpecification productSearchSpecification =
                new ProductSearchSpecification(productSearchCriteriaBuilder.build());
        return productRepository.findAll(productSearchSpecification);
    }
}
