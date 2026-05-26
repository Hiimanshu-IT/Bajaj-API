package com.bajaj.bfhl.exception;

import com.bajaj.bfhl.dto.BfhlResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

/**
 * Global exception handler — catches anything unexpected and returns
 * a clean JSON response with is_success = false.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors (e.g., missing "data" field).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BfhlResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST);
    }

    /**
     * Catch-all for any other runtime exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponse> handleGenericException(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<BfhlResponse> buildErrorResponse(HttpStatus status) {
        BfhlResponse errorResponse = BfhlResponse.builder()
                .isSuccess(false)
                .userId("")
                .email("")
                .rollNumber("")
                .oddNumbers(Collections.emptyList())
                .evenNumbers(Collections.emptyList())
                .alphabets(Collections.emptyList())
                .specialCharacters(Collections.emptyList())
                .sum("0")
                .concatString("")
                .build();

        return ResponseEntity.status(status).body(errorResponse);
    }
}
