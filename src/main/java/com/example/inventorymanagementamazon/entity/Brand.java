package com.example.inventorymanagementamazon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brands", schema = "inventory_management")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brands_id_seq_gen")
    @SequenceGenerator(name = "brands_id_seq_gen", sequenceName = "brands_id_seq", allocationSize = 1, schema = "inventory_management")
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;
}
