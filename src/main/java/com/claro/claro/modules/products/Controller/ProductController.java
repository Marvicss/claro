package com.claro.claro.modules.products.Controller;

import com.claro.claro.modules.products.dtos.CreateProductRequestDTO;
import com.claro.claro.modules.products.dtos.ProductResponseDTO;
import com.claro.claro.modules.products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid CreateProductRequestDTO dto) {
        ProductResponseDTO response = productService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable UUID id) {
        ProductResponseDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable UUID id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable UUID id,
            @RequestBody @Valid CreateProductRequestDTO productDTO) {

        ProductResponseDTO updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }




}
