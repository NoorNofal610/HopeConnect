package com.example.hopeconnectt.Exceptions;

import com.example.hopeconnectt.Models.Enumes.ErrorSeverity;

import com.example.hopeconnectt.Services.ErrorLogService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ErrorLogService errorLogService;

    public GlobalExceptionHandler(ErrorLogService errorLogService) {
        this.errorLogService = errorLogService;
    }

    @ExceptionHandler(InvalidProfileException.class)
    public ResponseEntity<String> handleInvalidProfile(InvalidProfileException ex, HttpServletRequest request) {
        errorLogService.logError(ex, ErrorSeverity.MEDIUM, this.getClass(), "handleInvalidProfile", request);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
        errorLogService.logError(ex, ErrorSeverity.LOW, this.getClass(), "handleUserNotFound", request);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrphanNotFoundException.class)
    public ResponseEntity<Object> handleOrphanNotFoundException(OrphanNotFoundException ex, HttpServletRequest request) {
        errorLogService.logError(ex, ErrorSeverity.LOW, this.getClass(), "handleOrphanNotFoundException", request);

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<Object> handleUnauthorizedAccessException(UnauthorizedAccessException ex, HttpServletRequest request) {
        errorLogService.logError(ex, ErrorSeverity.HIGH, this.getClass(), "handleUnauthorizedAccessException", request);

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("error", "Unauthorized");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DonationNotFoundException.class)
public ResponseEntity<Object> handleDonationNotFoundException(DonationNotFoundException ex, HttpServletRequest request) {
    errorLogService.logError(ex, ErrorSeverity.LOW, this.getClass(), "handleDonationNotFoundException", request);

    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.NOT_FOUND.value());
    body.put("error", "Not Found");
    body.put("message", ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
}
@ExceptionHandler(VolunteerNotFoundException.class)
public ResponseEntity<Object> handleVolunteerNotFoundException(VolunteerNotFoundException ex, HttpServletRequest request) {
    errorLogService.logError(ex, ErrorSeverity.LOW, this.getClass(), "handleVolunteerNotFoundException", request);

    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.NOT_FOUND.value());
    body.put("error", "Not Found");
    body.put("message", ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
}
@ExceptionHandler(DonorNotFoundException.class)
public ResponseEntity<Object> handleDonorNotFoundException(DonorNotFoundException ex, HttpServletRequest request) {
    errorLogService.logError(ex, ErrorSeverity.LOW, this.getClass(), "handleDonorNotFoundException", request);

    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.NOT_FOUND.value());
    body.put("error", "Not Found");
    body.put("message", ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
}
@ExceptionHandler(OrphanageNotFoundException.class)
public ResponseEntity<Object> handleOrphanageNotFoundException(OrphanageNotFoundException ex, HttpServletRequest request) {
    errorLogService.logError(ex, ErrorSeverity.LOW, this.getClass(), "handleOrphanageNotFoundException", request);

    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.NOT_FOUND.value());
    body.put("error", "Not Found");
    body.put("message", ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
}



    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex, HttpServletRequest request) {
        errorLogService.logError(ex, ErrorSeverity.HIGH, this.getClass(), "handleGenericException", request);
        return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
