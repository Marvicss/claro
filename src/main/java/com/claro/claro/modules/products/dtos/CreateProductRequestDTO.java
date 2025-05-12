package com.claro.claro.modules.products.dtos;



import com.claro.claro.modules.customer.model.Customer;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateProductRequestDTO(

        @NotBlank(message = "O nome é obrigatório.")
        String name,

        @NotBlank(message = "A descrição é obrigatória.")
        String description,

        @Min(value = 0, message = "O estoque não pode ser negativo.")
        int stock,

        @NotBlank(message = "A categoria é obrigatória.")
        String category,

        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero.")
        double price,

        @NotNull(message = "O usuário criador é obrigatório.")
        UUID createdBy


    @NotBlank(message = "O usuário criador é obrigatório.")
    private Customer createdBy; // Pode ser um UUID ou String representando o usuário
}

) {}

