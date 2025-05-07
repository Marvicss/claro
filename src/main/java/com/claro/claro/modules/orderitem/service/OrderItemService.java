package com.claro.claro.modules.orderitem.service;

import com.claro.claro.modules.orderitem.model.OrderItem;
import com.claro.claro.modules.orderitem.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderItem getOrderItemById(UUID id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order item not found: " + id));
    }

    public void deleteOrderItem(UUID id) {
        if (!orderItemRepository.existsById(id)) {
            throw new IllegalArgumentException("Order item not found: " + id);
        }
        orderItemRepository.deleteById(id);
    }
}