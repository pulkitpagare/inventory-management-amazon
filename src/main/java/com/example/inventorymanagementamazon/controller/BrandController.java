package com.example.inventorymanagementamazon.controller;

import com.example.inventorymanagementamazon.dto.BrandDTO;
import com.example.inventorymanagementamazon.entity.Brand;
import com.example.inventorymanagementamazon.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands(){
        List<Brand> brands = brandService.getAllBrands();
        return ResponseEntity.ok().body(brands);
    }

    @PostMapping
    public ResponseEntity<Brand> addBrand(@RequestBody @Valid BrandDTO brandDTO){
        Brand brand = brandService.addBrand(brandDTO);
        return ResponseEntity.ok().body(brand);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable(name = "id") Integer id){
        brandService.deleteById(id);
    }
}
