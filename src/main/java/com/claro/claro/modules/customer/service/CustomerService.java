package com.claro.claro.modules.customer.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.claro.claro.modules.customer.model.Customer;
import com.claro.claro.modules.customer.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer create(Customer customer) {
        String hashPassword = passwordEncoder.encode(customer.getPassword());

        customer.setPassword(hashPassword);
        Customer createdCustomer = customerRepository.save(customer);
        return createdCustomer;
    }
}
