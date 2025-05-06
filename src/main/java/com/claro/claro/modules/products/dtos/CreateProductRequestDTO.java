package com.claro.claro.modules.products.dtos;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    private String name;

    @NotBlank(message = "A descrição é obrigatória.")
    private String description;

    @Min(value = 0, message = "O estoque não pode ser negativo.")
    private int stock;

    @NotBlank(message = "A categoria é obrigatória.")
    private String category;

    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero.")
    private double price;
}