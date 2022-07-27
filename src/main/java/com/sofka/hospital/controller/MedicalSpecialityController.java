package com.sofka.hospital.controller;

import com.sofka.hospital.dto.MedicalSpecialityDTO;
import com.sofka.hospital.services.MedicalSpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/")
public class MedicalSpecialityController {

    @Autowired
    private MedicalSpecialityService medicalSpecialityService;

    @GetMapping("speciality")
    public List<MedicalSpecialityDTO> getAllSpecialities(){
        return medicalSpecialityService.getAllSpeciality();
    }

    @PostMapping("create/speciality")
    public MedicalSpecialityDTO createSpeciality(@RequestBody MedicalSpecialityDTO medicalSpecialityDTO){
        return medicalSpecialityService.createSpeciality(medicalSpecialityDTO);
    }
}
