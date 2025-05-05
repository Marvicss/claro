package com.claro.claro.modules.products.service;

import com.claro.claro.modules.products.dtos.CreateProductRequestDTO;
import com.claro.claro.modules.products.dtos.ProductResponseDTO;
import com.claro.claro.modules.products.exceptions.ProductFoundException;
import com.claro.claro.modules.products.exceptions.ProductNotFoundException;
import com.claro.claro.modules.products.mappers.ProductMapper;
import com.claro.claro.modules.products.model.Product;
import com.claro.claro.modules.products.repository.ProductRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO create(CreateProductRequestDTO dto) {
        validateProduct(dto);

        if (productRepository.existsByName(dto.getName())) {
            throw new ProductFoundException("Product with name " + dto.getName() + " already exists");
        }

        Product product = ProductMapper.toEntity(dto);
        productRepository.save(product);
        return new ProductMapper().toDTO(product);
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }


    public ProductResponseDTO getProductById(UUID id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Produto não encontrado com o id: " + id);
        }
        return ProductMapper.toDTO(productOptional.get());
    }

    public void deleteProductById(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Produto não encontrado com o id: " + id);
        }
        productRepository.deleteById(id);
    }


    public ProductResponseDTO updateProduct(UUID id, CreateProductRequestDTO productDTO) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Produto não encontrado com o id: " + id);
        }

        Product product = productOptional.get();

        // Atualizando os campos do produto com os dados fornecidos
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());

        productRepository.save(product);

        return ProductMapper.toDTO(product);
    }


    private void validateProduct(CreateProductRequestDTO dto) {
        if (dto.getStock() < 0) {
            throw new IllegalArgumentException("O estoque não pode ser negativo.");
        }

        if (dto.getPrice() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }
    }


}
