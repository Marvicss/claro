package com.claro.claro.modules.products.service;

import com.claro.claro.modules.customer.model.Customer;
import com.claro.claro.modules.customer.repository.CustomerRepository;
import com.claro.claro.modules.products.dtos.CreateProductRequestDTO;
import com.claro.claro.modules.products.dtos.ProductResponseDTO;
import com.claro.claro.modules.products.exceptions.ProductFoundException;
import com.claro.claro.modules.products.exceptions.ProductNotFoundException;
import com.claro.claro.modules.products.mappers.ProductMapper;
import com.claro.claro.modules.products.model.Product;
import com.claro.claro.modules.products.repository.ProductRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public ProductService(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public Product getProductEntityById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new com.claro.claro.modules.orders.exceptions.ProductNotFoundException("Product not found: " + productId));
    }

    public ProductResponseDTO create(CreateProductRequestDTO dto) {
        validateProduct(dto);

        if (productRepository.existsByName(dto.name())) {
            throw new ProductFoundException("Product with name " + dto.name() + " already exists");
        }

        Customer customer = customerRepository.findById(dto.createdBy())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + dto.createdBy()));

        Product product = ProductMapper.toEntity(dto);
        product.setCreatedBy(customer);
        productRepository.save(product);
        return new ProductMapper().toDTO(product);
    }

    public List<ProductResponseDTO> getAllProducts(Pageable pageable) {
        List<Product> products = productRepository.findAll(pageable).getContent();

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
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setStock(productDTO.stock());
        product.setCategory(productDTO.category());
        product.setPrice(productDTO.price());

        productRepository.save(product);

        return ProductMapper.toDTO(product);
    }


    private void validateProduct(CreateProductRequestDTO dto) {
        if (dto.stock() < 0) {
            throw new IllegalArgumentException("O estoque não pode ser negativo.");
        }

        if (dto.price() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }
    }


}
