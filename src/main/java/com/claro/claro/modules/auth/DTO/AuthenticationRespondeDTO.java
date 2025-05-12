package com.claro.claro.modules.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class AuthenticationRespondeDTO {
    private String token;
    private String email;


}
