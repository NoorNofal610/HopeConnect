package com.example.hopeconnectt.Reposotires;

import com.example.hopeconnectt.Models.Entity.ErrorLog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
    List<ErrorLog> findFirst10ByOrderByTimestampDesc(); // Fixed number
    }