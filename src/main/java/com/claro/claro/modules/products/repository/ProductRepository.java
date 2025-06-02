package com.claro.claro.modules.products.repository;

import com.claro.claro.modules.products.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Boolean existsByName(String name);
    Page<Product> findAll(Pageable pageable);

}
