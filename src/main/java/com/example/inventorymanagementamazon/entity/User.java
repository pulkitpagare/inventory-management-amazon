package com.example.inventorymanagementamazon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "users", schema = "inventory_management")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq_gen")
    @SequenceGenerator(name = "users_id_seq_gen", sequenceName = "users_id_seq", allocationSize = 1, schema = "inventory_management")
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    public User user;

    @Column(name = "address")
    private String address;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}
