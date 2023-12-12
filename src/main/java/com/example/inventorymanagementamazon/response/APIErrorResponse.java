package com.example.inventorymanagementamazon.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class APIErrorResponse {
    private Serializable result;
}
