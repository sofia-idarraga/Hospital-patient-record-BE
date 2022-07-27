package com.sofka.hospital.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "MedicalSpeciality")
@Table(name = "medical_speciality")
@Data
public class MedicalSpeciality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sp_id", nullable = false)
    private Long id;

    @Column(name = "sp_name", nullable = false, length = 100)
    @Size(min = 5, max = 100,message="criteria not met")
    private String name;

    @Column(name = "physician_in_charge", length = 45)
    @Size(min = 10, max = 45, message="criteria not met")
    private String physicianInCharge;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Patient> patients = new ArrayList<>();

    public MedicalSpeciality addPatient(Patient patient){
        this.patients.add(patient);
        return this;
    }

    public MedicalSpeciality removePatient(Patient patient){
        this.patients.remove(patient);
        return this;

    }
}
