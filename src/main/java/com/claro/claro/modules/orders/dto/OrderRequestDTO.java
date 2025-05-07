package com.claro.claro.modules.orders.dto;

import com.claro.claro.modules.orderitem.dto.OrderItemRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    @NotNull(message = "Código do usuário é obrigatório")
    private UUID userId;

    @NotBlank(message = "Região é obrigatória")
    private String region;

    private List<OrderItemRequestDTO> items = new ArrayList<>();
}