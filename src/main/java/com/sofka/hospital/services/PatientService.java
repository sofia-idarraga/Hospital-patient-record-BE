package com.sofka.hospital.services;

import com.sofka.hospital.dto.PatientDTO;
import com.sofka.hospital.entity.MedicalSpeciality;
import com.sofka.hospital.entity.Patient;
import com.sofka.hospital.repository.MedicalSpecialityRepository;
import com.sofka.hospital.repository.PatientRepository;
import com.sofka.hospital.services.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService implements IPatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    MedicalSpecialityRepository medicalSpecialityRepository;


    @Override
    public PatientDTO createPatient(PatientDTO patientDTO){
        MedicalSpeciality medicalSpeciality = medicalSpecialityRepository
                .findById(patientDTO.getFkSpecialityId()).get();
        Patient patient = convertDTOToPatient(patientDTO);
        medicalSpeciality.addPatient(patient);
        patient.setNumberOfAppointments(1L);
        patientRepository.save(patient);
        return convertPatientToDTO(patient);
    }

    @Transactional
    public void updateDate(PatientDTO patientDTO){
        Patient patient = patientRepository.findByDni(patientDTO.getDni(), patientDTO.getFkSpecialityId());
        String date = patient.getDatesOfAppointments();
        String newDate = date.concat("-").concat(patientDTO.getDatesOfAppointments());
        Long numberOfAppointment = patient.getNumberOfAppointments() + 1L;
        patientRepository.updateDate(patient.getId(), newDate, numberOfAppointment);
    }


    @Override
    public boolean deletePatient(PatientDTO patientDTO){
        MedicalSpeciality medicalSpeciality = medicalSpecialityRepository
                .findById(patientDTO.getFkSpecialityId()).get();
        Patient patient = patientRepository.findByDni(patientDTO.getDni(),patientDTO.getFkSpecialityId() );
        medicalSpeciality.removePatient(patient);
        medicalSpecialityRepository.save(medicalSpeciality);
        patientRepository.deleteById(patient.getId());
        return true;
    }
    private PatientDTO convertPatientToDTO(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setName(patient.getName());
        patientDTO.setAge(patient.getAge());
        patientDTO.setDni(patient.getDni());
        patientDTO.setDatesOfAppointments(patient.getDatesOfAppointments());
        patientDTO.setFkSpecialityId(patient.getFkSpecialityId());
        return patientDTO;
    }

    private Patient convertDTOToPatient(PatientDTO patientDTO){
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setAge(patientDTO.getAge());
        patient.setDni(patientDTO.getDni());
        patient.setDatesOfAppointments(patientDTO.getDatesOfAppointments());
        patient.setFkSpecialityId(patientDTO.getFkSpecialityId());
        return patient;    }
}
