package com.example.hopeconnectt.Reposotires;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hopeconnectt.Models.Entity.Donor;
import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    List<Donor> findByNameContaining(String name);
}
