package com.claro.claro.modules.orders.services;

import com.claro.claro.modules.orderitem.dto.OrderItemRequestDTO;
import com.claro.claro.modules.orderitem.model.OrderItem;
import com.claro.claro.modules.orders.dto.OrderRequestDTO;
import com.claro.claro.modules.orders.dto.OrderResponseDTO;
import com.claro.claro.modules.orders.exceptions.OrderNotFoundException;
import com.claro.claro.modules.orders.mapper.OrderMapper;
import com.claro.claro.modules.orders.model.Orders;
import com.claro.claro.modules.orders.repository.OrderRepository;
import com.claro.claro.modules.products.model.Product;
import com.claro.claro.modules.products.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    public OrderResponseDTO createOrder(OrderRequestDTO orderDTO) {
        Orders order = new Orders();
        order.setUserId(orderDTO.getUserId());
        order.setRegion(orderDTO.getRegion());

        List<OrderItem> items = orderDTO.getItems().stream().map(itemDTO -> {
            OrderItem item = new OrderItem();
            item.setProductId(itemDTO.getProductId());
            item.setQuantity(itemDTO.getQuantity());
            item.setPriceUnit(productService.getProductEntityById(itemDTO.getProductId()).getPrice());
            item.setSubtotal(item.getPriceUnit() * item.getQuantity()); // Calcula o subtotal
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());

        order.setItems(items);

        // Calcula o totalValue do pedido
        double totalValue = items.stream().mapToDouble(OrderItem::getSubtotal).sum();
        order.setTotalValue(totalValue);

        Orders savedOrder = orderRepository.save(order);

        return OrderMapper.toDTO(savedOrder);
    }

    public OrderResponseDTO getOrderById(UUID id) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));
        return convertToResponseDTO(order);
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<OrderResponseDTO> getOrdersByUserId(UUID userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<OrderResponseDTO> getOrdersByRegion(String region) {
        return orderRepository.findByRegion(region).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public OrderResponseDTO addItemToOrderFromDTO(UUID orderId, OrderItemRequestDTO itemDTO) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        Product product = productService.getProductEntityById(itemDTO.getProductId());

        OrderItem item = new OrderItem();
        item.setProductId(itemDTO.getProductId());
        item.setQuantity(itemDTO.getQuantity());
        item.setPriceUnit(product.getPrice());
        item.setSubtotal(product.getPrice() * itemDTO.getQuantity());
        order.addItem(item);

        Orders savedOrder = orderRepository.save(order);
        return convertToResponseDTO(savedOrder);
    }

    public void deleteOrder(UUID id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order not found: " + id);
        }
        orderRepository.deleteById(id);
    }

    private OrderResponseDTO convertToResponseDTO(Orders order) {
        return modelMapper.map(order, OrderResponseDTO.class);
    }
}