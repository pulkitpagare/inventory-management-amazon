package com.example.inventorymanagementamazon.service;

import com.example.inventorymanagementamazon.annotation.LogExecutionTime;
import com.example.inventorymanagementamazon.dto.CategoryDTO;
import com.example.inventorymanagementamazon.entity.Category;
import com.example.inventorymanagementamazon.exception.NotFoundException;
import com.example.inventorymanagementamazon.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @LogExecutionTime
    public List<Category> findAllCategory(){
        return categoryRepository.findAll();
    }

    @LogExecutionTime
    public Category add(CategoryDTO categoryDTO){
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();
        return categoryRepository.save(category);
    }

    @LogExecutionTime
    public void delete(BigInteger id){
        Optional<Category> category = categoryRepository.findById(id);
        category.ifPresentOrElse( categoryEntity -> categoryRepository.delete(categoryEntity), () ->  {
            throw new NotFoundException("Category does not exist");
        });
    }

}
