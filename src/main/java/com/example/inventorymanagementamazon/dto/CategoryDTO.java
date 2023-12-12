package com.example.inventorymanagementamazon.dto;

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

    private String name;

    Set<ProductDTO> products;
}
