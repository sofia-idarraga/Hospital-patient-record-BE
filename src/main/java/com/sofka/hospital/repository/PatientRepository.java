package com.sofka.hospital.repository;

import com.sofka.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT pat FROM Patient pat WHERE pat.dni = :dni")
    Patient findByDni(Long dni);

}