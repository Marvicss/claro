package com.claro.claro.modules.orders.mapper;

import com.claro.claro.modules.orderitem.dto.OrderItemDTO;
import com.claro.claro.modules.orderitem.model.OrderItem;
import com.claro.claro.modules.orders.dto.OrderResponseDTO;
import com.claro.claro.modules.orders.model.Orders;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponseDTO toDTO(Orders order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getUserId(),
                order.getRegion(),
                order.getOrderDate(),
                order.getTotalValue(),
                order.getItems().stream()
                        .map(OrderMapper::toOrderItemDTO)
                        .collect(Collectors.toList())
        );
    }

    private static OrderItemDTO toOrderItemDTO(OrderItem item) {
        return new OrderItemDTO(
                item.getId(),
                item.getProductId(),
                item.getQuantity(),
                item.getSubtotal()
        );
    }
}
