package com.example.inventorymanagementamazon.controller;

import com.example.inventorymanagementamazon.dto.CategoryDTO;
import com.example.inventorymanagementamazon.entity.Category;
import com.example.inventorymanagementamazon.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> allCategory = categoryService.findAllCategory();
        return ResponseEntity.ok().body(allCategory);
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody @Valid CategoryDTO categoryDTO){
        Category category = categoryService.add(categoryDTO);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping("/{id}")
    public void deleteCategory(@PathVariable(name = "id") BigInteger id){
        categoryService.delete(id);
    }
}
