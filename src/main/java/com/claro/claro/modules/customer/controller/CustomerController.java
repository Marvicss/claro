package com.claro.claro.modules.customer.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.claro.claro.modules.customer.dtos.CustomerResponseDTO;
import com.claro.claro.modules.customer.model.Customer;
import com.claro.claro.modules.customer.service.CustomerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/")
    public ResponseEntity<CustomerResponseDTO> create(@RequestBody Customer customer) {
        CustomerResponseDTO createdCustomer = customerService.create(customer);
        return ResponseEntity.ok().body(createdCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable UUID id) {
        CustomerResponseDTO customer = customerService.findById(id);
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerResponseDTO>> findAll() {
        List<CustomerResponseDTO> customers = customerService.findAll();
        return ResponseEntity.ok().body(customers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable UUID id, @RequestBody Customer customer) {
        CustomerResponseDTO updatedCustomer = customerService.update(id, customer);
        return ResponseEntity.ok().body(updatedCustomer);
    }

    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        Boolean hasDeleted = customerService.delete(id);
        return ResponseEntity.ok().body(hasDeleted);
    }

}
