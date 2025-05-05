package com.claro.claro.modules.products.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private UUID id;
    private String name;
    private String description;
    private int stock;
    private String category;
    private double price;
    private LocalDateTime createdAt;

}