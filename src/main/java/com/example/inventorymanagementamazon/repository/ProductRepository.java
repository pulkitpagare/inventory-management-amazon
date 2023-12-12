package com.example.inventorymanagementamazon.repository;

import com.example.inventorymanagementamazon.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProductRepository extends JpaRepository<Product, BigInteger> {
}
