package com.pm.pateintService.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        log.warn("Validation failed: {} field error(s) detected", ex.getBindingResult().getFieldErrorCount());

        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(e -> {
            log.debug("Validation error -> field: '{}', rejected value: '{}', message: '{}'",
                    e.getField(), e.getRejectedValue(), e.getDefaultMessage());
            errors.put(e.getField(), e.getDefaultMessage());
        });

        log.warn("Returning 400 Bad Request with validation errors: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsExcpetion.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyExistsException(EmailAlreadyExistsExcpetion ex){
        log.warn("Duplicate email attempt detected. Message: '{}'", ex.getMessage());

        Map<String,String> errors = new HashMap<>();
        errors.put("error", "Email Already Exists");
        errors.put("message", ex.getMessage());

        log.debug("Returning 400 Bad Request for duplicate email with body: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String,String>> handlePatientNotFoundException(PatientNotFoundException ex){
        log.warn("Patient Not found. Message: '{}'", ex.getMessage());
        Map<String,String> errors = new HashMap<>();
        errors.put("error", "Patient Not Found");
        errors.put("message", ex.getMessage());

        log.debug("Returning 400 Bad Request for Patient not found: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

}