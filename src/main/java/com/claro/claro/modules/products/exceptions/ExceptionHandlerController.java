package com.claro.claro.modules.products.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.persistence.EntityExistsException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ProductFoundException.class)
    public ResponseEntity<Object> handleProductFoundException(ProductFoundException ex) {

        ApiError apiError = new ApiError(
                ex.getMessage(),
                "PRODUCT_ALREADY_EXISTS",
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {
        ApiError apiError = new ApiError(
                ex.getMessage(),
                "PRODUCT_NOT_FOUND",
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }


}
