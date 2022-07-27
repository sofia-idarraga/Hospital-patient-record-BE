package com.sofka.hospital.repository;

import com.sofka.hospital.entity.MedicalSpeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalSpecialityRepository extends JpaRepository<MedicalSpeciality, Long> {
}