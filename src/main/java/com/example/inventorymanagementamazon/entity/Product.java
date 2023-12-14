package com.example.inventorymanagementamazon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products", schema = "inventory_management")
@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_id_seq_gen")
    @SequenceGenerator(name = "products_id_seq_gen", sequenceName = "products_id_seq", allocationSize = 1, schema = "inventory_management")
    private BigInteger id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Integer price;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "product_category", schema = "inventory_management",
            joinColumns = @JoinColumn(name = "product_id", table = "products"),
            inverseJoinColumns = @JoinColumn(name = "category_id", table = "category")
    )
    private Set<Category> categories;

    @ManyToOne(targetEntity = Brand.class, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    Brand brand;
}


