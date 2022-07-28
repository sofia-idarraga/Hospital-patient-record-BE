package com.sofka.hospital.services;
import com.sofka.hospital.dto.MedicalSpecialityDTO;
import com.sofka.hospital.dto.PatientDTO;
import com.sofka.hospital.entity.MedicalSpeciality;
import com.sofka.hospital.entity.Patient;
import com.sofka.hospital.repository.MedicalSpecialityRepository;
import com.sofka.hospital.services.interfaces.IMedicalSpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalSpecialityService implements IMedicalSpecialityService {

    @Autowired
    private MedicalSpecialityRepository medicalSpecialityRepository;

    @Override
    public List<MedicalSpecialityDTO> getAllSpeciality(){
        return medicalSpecialityRepository.findAll()
                .stream()
                .map(this::convertSpecialityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalSpecialityDTO createSpeciality(MedicalSpecialityDTO medicalSpecialityDTO) {
        MedicalSpeciality medicalSpeciality = new MedicalSpeciality();
        medicalSpeciality.setName(medicalSpecialityDTO.getName());
        medicalSpeciality.setPhysicianInCharge(medicalSpecialityDTO.getPhysicianInCharge());
        return convertSpecialityToDTO(medicalSpecialityRepository.save(medicalSpeciality));
    }

    @Override
    public boolean deleteSpeciality(MedicalSpecialityDTO medicalSpecialityDTO){
    MedicalSpeciality medicalSpeciality = medicalSpecialityRepository.findById(medicalSpecialityDTO.getSpecialityId()).get();
    if(medicalSpeciality.getPatients().size() == 0){
        medicalSpecialityRepository.deleteById(medicalSpeciality.getId());
        return true;
        }
    return false;
    }

    @Transactional
    public void updateName(Long id, String name){
        medicalSpecialityRepository.updateName(id, name);
    }

    @Transactional
    public void updateSpeciality(Long id, String name, String physicianInCharge){
        medicalSpecialityRepository.updateMedicalSpeciality(id, name, physicianInCharge);
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

    private MedicalSpecialityDTO convertSpecialityToDTO(MedicalSpeciality medicalSpeciality){
        MedicalSpecialityDTO medicalSpecialityDTO = new MedicalSpecialityDTO();
        medicalSpecialityDTO.setSpecialityId(medicalSpeciality.getId());
        medicalSpecialityDTO.setName(medicalSpeciality.getName());
        medicalSpecialityDTO.setPhysicianInCharge(medicalSpeciality.getPhysicianInCharge());
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (Patient patient :medicalSpeciality.getPatients() ) {
            patientDTOS.add(convertPatientToDTO(patient));
        }
        medicalSpecialityDTO.setPatients(patientDTOS);
        return medicalSpecialityDTO;
    }
}
