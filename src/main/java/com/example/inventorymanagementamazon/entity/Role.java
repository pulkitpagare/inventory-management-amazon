package com.example.inventorymanagementamazon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name = "role", schema = "inventory_management")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq_gen")
    @SequenceGenerator(name = "role_id_seq_gen", sequenceName = "role_id_seq", allocationSize = 1, schema = "inventory_management")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;



}
