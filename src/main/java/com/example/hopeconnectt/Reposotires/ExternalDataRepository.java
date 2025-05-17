package com.example.hopeconnectt.Reposotires;


import com.example.hopeconnectt.Models.Entity.ExternalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalDataRepository extends JpaRepository<ExternalData, Long> {
}
