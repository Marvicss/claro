package com.claro.claro.modules.customer.dtos;

import java.util.UUID;

import com.claro.claro.enums.RoleCustomerEnum;

public record CustomerResponseDTO(
        UUID id,
        String name,
        String email,
        String position,
        String city,
        String state,
        RoleCustomerEnum role

) {

}
