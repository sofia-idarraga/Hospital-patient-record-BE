package com.sofka.hospital.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity(name = "Patient")
@Table(name = "patient")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pat_id", nullable = false)
    private Long id;

    @Column(name = "pat_name", nullable = false, length = 45)
    @Size(min = 10, max = 45, message="criteria not met")
    private String name;

    @Min(0)
    @Positive
    @Column(name = "pat_age", nullable = false)
    private int age;

    @Column(name = "pat_dni", nullable = false)
    private Long dni;

    @Column(name = "pat_dates_of_appointments")
    private String datesOfAppointments;

    @Column(name = "pat_number_of_appointments")
    private Long numberOfAppointments;

    private Long fkSpecialityId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return age == patient.age && Objects.equals(id, patient.id) && Objects.equals(name, patient.name) && Objects.equals(dni, patient.dni) && Objects.equals(numberOfAppointments, patient.numberOfAppointments) && Objects.equals(fkSpecialityId, patient.fkSpecialityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, dni, numberOfAppointments, fkSpecialityId);
    }
}
