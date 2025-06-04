package com.claro.claro.modules.orders.repository;

import com.claro.claro.modules.orders.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {
    Page<Orders> findByUserId(UUID userId, Pageable pageable);
    Page<Orders> findByRegion(String region, Pageable pageable);
}
