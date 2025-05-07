package com.claro.claro.modules.orders.controller;

import com.claro.claro.modules.orderitem.dto.OrderItemRequestDTO;
import com.claro.claro.modules.orders.dto.OrderRequestDTO;
import com.claro.claro.modules.orders.dto.OrderResponseDTO;
import com.claro.claro.modules.orders.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderDTO) {
        return new ResponseEntity<>(orderService.createOrder(orderDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<OrderResponseDTO> addItemToOrder(
            @PathVariable UUID orderId,
            @Valid @RequestBody OrderItemRequestDTO itemDTO) {
        return ResponseEntity.ok(orderService.addItemToOrderFromDTO(orderId, itemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}