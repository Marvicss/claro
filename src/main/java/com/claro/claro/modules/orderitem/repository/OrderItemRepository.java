package com.claro.claro.modules.orderitem.repository;

import com.claro.claro.modules.orderitem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
