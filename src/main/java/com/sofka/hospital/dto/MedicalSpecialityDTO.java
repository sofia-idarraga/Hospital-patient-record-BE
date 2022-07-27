package com.sofka.hospital.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MedicalSpecialityDTO {
    private Long specialityId;
    private String name;
    private String physicianInCharge;
    private List<PatientDTO> patients = new ArrayList<>();
}
