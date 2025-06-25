package com.arifin.blog.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiHandler{
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handler(ApiException exception){
        List<String> errorMessages = List.of(exception.getMessage());
        ApiResponse response = ApiResponse.builder().ErrorMessage(errorMessages).build();
        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder()
                .ErrorMessage(errors)
                .build());
    }
}
