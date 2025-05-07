package com.claro.claro.modules.orders.dto;

import com.claro.claro.modules.orderitem.dto.OrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private UUID id;
    private UUID userId;
    private String region;
    private LocalDateTime orderDate;
    private double totalValue;
    private List<OrderItemDTO> items = new ArrayList<>();
}