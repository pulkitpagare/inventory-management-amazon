package com.example.inventorymanagementamazon.dto;

import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchCriteria {
    private String name;
    private List<BigInteger> ids;
    private Integer brandId;

}
