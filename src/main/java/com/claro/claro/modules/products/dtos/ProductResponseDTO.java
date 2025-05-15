package com.claro.claro.modules.products.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDTO(
        UUID id,
        String name,
        String description,
        int stock,
        String category,
        double price,
        String imgUrl,
        LocalDateTime createdAt,
        CustomerDTO createdBy) {
    public record CustomerDTO(UUID id, String name, String email) {
    }

}
