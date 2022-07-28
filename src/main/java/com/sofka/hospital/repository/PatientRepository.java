package com.sofka.hospital.repository;

import com.sofka.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT pat FROM Patient pat WHERE pat.dni = :dni AND " +
            "pat.fkSpecialityId = :fkSpecialityId ")
    Patient findByDni(
            @Param(value = "dni") Long dni,
            @Param(value = "fkSpecialityId")Long fkSpecialityId
    );

    @Modifying
    @Query("UPDATE Patient pat SET pat.datesOfAppointments = :datesOfAppointments " +
            "WHERE pat.id = :id")
    void updateDate(
            @Param(value = "id") Long id,
            @Param(value = "datesOfAppointments") String datesOfAppointments
    );

}