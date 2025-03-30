package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.Models.Entity.ErrorLog;
import com.example.hopeconnectt.Models.Entity.User;
import com.example.hopeconnectt.Models.Enumes.ErrorSeverity;
import com.example.hopeconnectt.Reposotires.ErrorLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ErrorLogService {
    private final ErrorLogRepository errorLogRepository;
    private final UserService userService;

    @Transactional
    public void logError(Exception exception, ErrorSeverity severity, Class<?> sourceClass, String sourceMethod) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            user = userService.findByUsername(username).orElse(null);
        }

        ErrorLog errorLog = new ErrorLog();
        errorLog.setErrorMessage(exception.getMessage());
        errorLog.setSeverity(severity);
        errorLog.setUser(user);
        errorLog.setSourceClass(sourceClass.getName());
        errorLog.setSourceMethod(sourceMethod);
        errorLog.setStackTrace(Arrays.toString(exception.getStackTrace()));

        errorLogRepository.save(errorLog);
    }

    @Transactional(readOnly = true)
    public List<ErrorLog> getRecentErrors(int count) {
        return errorLogRepository.findFirst10ByOrderByTimestampDesc();
    }
}