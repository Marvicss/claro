package com.claro.claro.modules.customer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.claro.claro.modules.customer.model.Customer;
import com.claro.claro.modules.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/")
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.create(customer);
        return ResponseEntity.ok().body(createdCustomer);
    }
}
