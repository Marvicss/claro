package com.claro.claro.modules.customer.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.claro.claro.modules.customer.dtos.CustomerResponseDTO;
import com.claro.claro.modules.customer.mappers.CustomerResponseMapper;
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

    public CustomerResponseDTO create(Customer customer) {
        String hashPassword = passwordEncoder.encode(customer.getPassword());

        customer.setPassword(hashPassword);
        Customer createdCustomer = customerRepository.save(customer);
        return CustomerResponseMapper.customerToResponseDTO(createdCustomer);
    }

    public CustomerResponseDTO findById(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return CustomerResponseMapper.customerToResponseDTO(customer);
    }

    public List<CustomerResponseDTO> findAll() {
        return customerRepository.findAll().stream().map(CustomerResponseMapper::customerToResponseDTO)
                .collect(Collectors.toList());
    }

    public CustomerResponseDTO update(UUID id, Customer customer) {
        if (customerRepository.existsById(id)) {
            customer.setId(id);
            String hashPassword = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(hashPassword);
            Customer updatedCustomer = customerRepository.save(customer);
            return CustomerResponseMapper.customerToResponseDTO(updatedCustomer);
        }

        return null;
    }

    public Boolean delete(UUID id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
