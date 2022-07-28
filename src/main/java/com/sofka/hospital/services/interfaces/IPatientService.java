package com.sofka.hospital.services.interfaces;

import com.sofka.hospital.dto.PatientDTO;

public interface IPatientService {

    PatientDTO createPatient(PatientDTO patientDTO);

    boolean deletePatient(PatientDTO patientDTO);
}
