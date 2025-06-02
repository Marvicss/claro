package com.claro.claro.modules.auth.controller;

import com.claro.claro.modules.auth.DTO.AuthenticationRespondeDTO;
import com.claro.claro.modules.auth.jwt.JwtService;
import com.claro.claro.modules.auth.records.LoginForm;
import com.claro.claro.modules.auth.service.SessionService;
import com.claro.claro.modules.customer.model.Customer;
import com.claro.claro.modules.customer.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class LoginController {

    @Autowired
    private final AuthenticationManager auth;

    @Autowired
    private JwtService jwtService;

    private  PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;




    public LoginController(JwtService jwtService, AuthenticationManager auth, PasswordEncoder passwordEncoder, CustomerRepository customerRepository) {
        this.auth = auth;
        this.jwtService = jwtService;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginForm form) {
        try {
            Customer customer = this.customerRepository.findByEmail(form.email())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            
            if (!passwordEncoder.matches(form.password(), customer.getPassword())) {
                return ResponseEntity.badRequest().body("Credenciais inválidas.");
            }

           
            String token = this.jwtService.gerarTokenCustomer(customer);
            return ResponseEntity.ok(new AuthenticationRespondeDTO(token, customer.getEmail()));

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao realizar login.");
        }
    }



}
