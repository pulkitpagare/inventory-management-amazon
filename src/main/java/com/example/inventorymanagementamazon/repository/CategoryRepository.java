package com.example.inventorymanagementamazon.repository;

import com.example.inventorymanagementamazon.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CategoryRepository extends JpaRepository<Category, BigInteger> {

}
