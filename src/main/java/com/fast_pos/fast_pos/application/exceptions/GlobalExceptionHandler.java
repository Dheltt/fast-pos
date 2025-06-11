package com.fast_pos.fast_pos.application.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import org.springdoc.core.providers.HateoasHalProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //validation error @valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,Object> body = new HashMap<>();
        body.put("timestamp",LocalDateTime.now());
        body.put("error", "Validation failded");
        body.put("status",HttpStatus.BAD_REQUEST.value());
        body.put("message","Invalid input");
        Map<String,String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fielError -> fielError.getDefaultMessage(),
                        (msg1,msg2) -> msg1
                ));
        body.put("fieldErrors",fieldErrors);
        return body;
    }
    //ConstraintViolation (for example  @PathVariable, @RequestParam)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleConstraintViolation(ConstraintViolationException ex){
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "error","Constraint  violation",
                "status",HttpStatus.BAD_REQUEST.value(),
                "message",ex.getMessage()
        );
    }
    //incorrect param (for example UUID inválido)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "error", "Type Mismatch",
                "status", HttpStatus.BAD_REQUEST.value(),
                "message", String.format("Invalid value for parameter '%s': '%s'", ex.getName(), ex.getValue())
        );
    }

    //Malformed JSON or incorrectly typed fields
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "error", "Malformed JSON Request",
                "status", HttpStatus.BAD_REQUEST.value(),
                "message", ex.getMessage()
        );
    }

    // Entity not found (includes ProductNotFoundException if extends from EntityNotFoundException)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleEntityNotFound(EntityNotFoundException ex) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "error", "Not Found",
                "status", HttpStatus.NOT_FOUND.value(),
                "message", ex.getMessage()
        );
    }

    //Invalid Arguments
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleIllegalArgument(IllegalArgumentException ex) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "error", "Bad Request",
                "status", HttpStatus.BAD_REQUEST.value(),
                "message", ex.getMessage()
        );
    }

    //General Errors
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleGeneralException(Exception ex) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "error", "Internal Server Error",
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "message", ex.getMessage()
        );
    }
}