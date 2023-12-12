package com.example.inventorymanagementamazon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "category", schema = "inventory_management")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq_gen")
    @SequenceGenerator(name = "category_id_seq_gen", sequenceName = "category_id_seq", allocationSize = 1, schema = "inventory_management")
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "categories")
    Set<Product> products;
}
