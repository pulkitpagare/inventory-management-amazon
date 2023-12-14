package com.example.inventorymanagementamazon.repository;

import com.example.inventorymanagementamazon.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, BigInteger>, JpaSpecificationExecutor<Product> {
}
