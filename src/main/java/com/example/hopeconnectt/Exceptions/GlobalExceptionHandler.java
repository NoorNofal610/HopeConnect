package com.example.hopeconnectt.Exceptions;

import com.example.hopeconnectt.Services.ErrorLogService;
import com.example.hopeconnectt.Models.Enumes.ErrorSeverity;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final ErrorLogService errorLogService;

    public GlobalExceptionHandler(ErrorLogService errorLogService) {
        this.errorLogService = errorLogService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        errorLogService.logError(ex, ErrorSeverity.HIGH, 
            ex.getStackTrace()[0].getClass(), 
            ex.getStackTrace()[0].getMethodName());
        
        return new ResponseEntity<>(
            Map.of(
                "message", "An error occurred",
                "error", ex.getMessage(),
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value()
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}