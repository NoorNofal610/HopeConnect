package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.Models.Entity.ErrorLog;
import com.example.hopeconnectt.Models.Entity.User;
import com.example.hopeconnectt.Models.Enumes.ErrorSeverity;
import com.example.hopeconnectt.Reposotires.ErrorLogRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

@Service

public class ErrorLogService {

    private final ErrorLogRepository errorLogRepository;

    @Autowired
    public ErrorLogService(ErrorLogRepository errorLogRepository) {
        this.errorLogRepository = errorLogRepository;
    }

    public void saveError_Log(String message) {
        ErrorLog errorLog = new ErrorLog(message, LocalDateTime.now());
        errorLogRepository.save(errorLog);
    }
    public void logError(Exception ex, ErrorSeverity severity, Class<?> sourceClass, String method, HttpServletRequest request) {
    String message = String.format("[%s] %s: %s (at %s - %s)", severity, ex.getClass().getSimpleName(), ex.getMessage(), sourceClass.getSimpleName(), method);
    String fullLog = message + " | Path: " + request.getRequestURI();

    ErrorLog log = new ErrorLog(fullLog, LocalDateTime.now());
    errorLogRepository.save(log);
}

}
