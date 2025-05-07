package com.claro.claro.modules.orderitem.mapper;

import com.claro.claro.modules.orderitem.dto.OrderItemDTO;
import com.claro.claro.modules.orderitem.model.OrderItem;

public class OrderItemMapper {
    public static OrderItemDTO toDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getProductId(),
                orderItem.getQuantity(),
                orderItem.getSubtotal()
        );
    }
}