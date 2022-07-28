package com.sofka.hospital.services.interfaces;

import com.sofka.hospital.dto.MedicalSpecialityDTO;

import java.util.List;

public interface IMedicalSpecialityService {
    List<MedicalSpecialityDTO> getAllSpeciality();
    MedicalSpecialityDTO createSpeciality(MedicalSpecialityDTO medicalSpecialityDTO);

    boolean deleteSpeciality(MedicalSpecialityDTO medicalSpecialityDTO);
}
