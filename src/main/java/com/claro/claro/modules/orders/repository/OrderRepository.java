package com.claro.claro.modules.orders.repository;

import com.claro.claro.modules.orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {
    List<Orders> findByUserId(UUID userId);
    List<Orders> findByRegion(String region);
}
