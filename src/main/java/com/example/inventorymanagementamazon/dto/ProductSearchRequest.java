package com.example.inventorymanagementamazon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchRequest {
    @JsonProperty("product-name")
    String productName;

    @JsonProperty("brand-name")
    String brandName;

    @JsonProperty("category-name")
    String categoryName;
}
