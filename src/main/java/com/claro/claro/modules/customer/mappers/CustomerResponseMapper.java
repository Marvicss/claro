package com.claro.claro.modules.customer.mappers;

import com.claro.claro.modules.customer.dtos.CustomerResponseDTO;
import com.claro.claro.modules.customer.model.Customer;

public class CustomerResponseMapper {

    public static CustomerResponseDTO customerToResponseDTO(Customer customer) {
        return new CustomerResponseDTO(customer.getId(), customer.getName(), customer.getEmail(),
                customer.getPosition(), customer.getCity(), customer.getState(), customer.getRole());
    }
}
