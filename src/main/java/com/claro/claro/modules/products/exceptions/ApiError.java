package com.claro.claro.modules.products.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String message;
    private String code;
    private int status;
}
