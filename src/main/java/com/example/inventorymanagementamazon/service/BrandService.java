package com.example.inventorymanagementamazon.service;

import com.example.inventorymanagementamazon.dto.BrandDTO;
import com.example.inventorymanagementamazon.entity.Brand;
import com.example.inventorymanagementamazon.exception.NotFoundException;
import com.example.inventorymanagementamazon.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }

    public Brand brandById(Integer id){
        Optional<Brand> brand = brandRepository.findById(id);
        return brand.orElseThrow(() -> new NotFoundException("brand does not exist"));
    }
    public Brand addBrand(BrandDTO brandDTO){
        Brand brand = Brand.builder()
                .name(brandDTO.getName())
                .description(brandDTO.getDescription())
                .build();
        return brandRepository.save(brand);
    }

    public void deleteById(Integer id){
        brandById(id);
        brandRepository.deleteById(id);
    }
}
