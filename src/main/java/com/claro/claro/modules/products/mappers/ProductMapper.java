package com.claro.claro.modules.products.mappers;

import com.claro.claro.modules.customer.model.Customer;
import com.claro.claro.modules.products.dtos.CreateProductRequestDTO;
import com.claro.claro.modules.products.dtos.ProductResponseDTO;
import com.claro.claro.modules.products.model.Product;

public class ProductMapper {

    public static Product toEntity(CreateProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setStock(dto.stock());
        product.setCategory(dto.category());
        product.setPrice(dto.price());
        product.setImgUrl(dto.imgUrl());
        return product;
    }

    public static ProductResponseDTO toDTO(Product product) {
        Customer createdBy = product.getCreatedBy();
        ProductResponseDTO.CustomerDTO createdByDTO = new ProductResponseDTO.CustomerDTO(
                createdBy.getId(),
                createdBy.getName(),
                createdBy.getEmail());

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStock(),
                product.getCategory(),
                product.getPrice(),
                product.getImgUrl(),
                product.getCreatedAt(),
                createdByDTO);
    }
}
