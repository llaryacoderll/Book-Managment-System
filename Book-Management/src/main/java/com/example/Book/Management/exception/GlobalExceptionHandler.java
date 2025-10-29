package com.example.Book.Management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ReponseEntity<Map<String,Object>> handleBookNotFound(BookNotFoundException ex){
        Map<String,Object> error = new HashMap<>();
        error.put("Timestamp", LocalDateTime.now());
        error.put("Status", HttpStatus.NOT_FOUND.value());
        error.put("error","Book Not Found");
        error.put("mmessage",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
