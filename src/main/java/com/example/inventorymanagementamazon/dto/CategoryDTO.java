package com.example.inventorymanagementamazon.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigInteger;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class CategoryDTO {

    private BigInteger id;

    @NotEmpty(message = "name should not be empty")
    private String name;
}
