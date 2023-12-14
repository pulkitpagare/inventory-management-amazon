package com.example.inventorymanagementamazon.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {


    private BigInteger id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "brand is required")
    private String brandName;

    private String description;

    @Digits(message = "should be an Integer", integer = Integer.MAX_VALUE, fraction = 0)
    private Integer price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotEmpty(message = "should have at least one category")
    private Set<BigInteger> categories;


}


