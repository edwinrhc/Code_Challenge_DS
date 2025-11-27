package com.erhc.alumnoservice.app.api;

import com.erhc.alumnoservice.app.service.DuplicateIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateIdException.class)
    public ResponseEntity<Map<String,String>> handleDuplicatedId(DuplicateIdException ex){
        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(WebExchangeBindException ex){
       Map<String, Object> body = new HashMap<>();
       body.put("status", HttpStatus.BAD_REQUEST.value());

       Map<String,String> errors = new HashMap<>();
       ex.getFieldErrors().forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));

       body.put("errors", errors);
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
