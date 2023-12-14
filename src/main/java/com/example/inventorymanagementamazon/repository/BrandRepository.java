package com.example.inventorymanagementamazon.repository;

import com.example.inventorymanagementamazon.entity.Brand;
import com.example.inventorymanagementamazon.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
