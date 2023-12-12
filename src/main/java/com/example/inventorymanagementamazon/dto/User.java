package com.example.inventorymanagementamazon.dto;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class User {

    private BigInteger id;

    private String name;

    public User user;

    private String address;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
