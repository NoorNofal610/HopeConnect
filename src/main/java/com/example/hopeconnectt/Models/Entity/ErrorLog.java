package com.example.hopeconnectt.Models.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import com.example.hopeconnectt.Models.Enumes.ErrorSeverity;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "error_logs")
public class ErrorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @CreationTimestamp
    private LocalDateTime timestamp;

    @Column(columnDefinition = "TEXT")
    private String errorMessage;

    @Enumerated(EnumType.STRING)
    private ErrorSeverity severity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String sourceClass;
    private String sourceMethod;
    private String stackTrace;
}

