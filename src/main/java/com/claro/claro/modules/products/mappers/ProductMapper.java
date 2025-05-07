package com.claro.claro.modules.products.mappers;


import com.claro.claro.modules.products.dtos.CreateProductRequestDTO;
import com.claro.claro.modules.products.dtos.ProductResponseDTO;
import com.claro.claro.modules.products.model.Product;

public class ProductMapper {

    public static Product toEntity(CreateProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setStock(dto.getStock());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setCreatedBy(dto.getCreatedBy());
        return product;
    }

    public static ProductResponseDTO toDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStock(),
                product.getCategory(),
                product.getPrice(),
                product.getCreatedAt()
        );
    }
}
