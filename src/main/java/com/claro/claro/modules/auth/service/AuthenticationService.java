package com.claro.claro.modules.auth.service;

import com.claro.claro.modules.auth.DTO.AuthenticationRespondeDTO;
import com.claro.claro.modules.auth.jwt.JwtService;
import com.claro.claro.modules.customer.model.Customer;
import com.claro.claro.modules.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CustomerRepository customerRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationRespondeDTO authenticate(AuthenticationRespondeDTO authenticationRespondeDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRespondeDTO.getEmail(),
                        authenticationRespondeDTO.getToken()
                )
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);


        Customer customer = customerRepository.findByEmail(authenticationRespondeDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));


        String jwtToken = jwtService.gerarTokenCustomer(customer);


        return new AuthenticationRespondeDTO(jwtToken, customer.getEmail());
    }



}
