package com.sofka.hospital.controller;

import com.sofka.hospital.dto.MedicalSpecialityDTO;
import com.sofka.hospital.dto.PatientDTO;
import com.sofka.hospital.entity.Patient;
import com.sofka.hospital.services.MedicalSpecialityService;
import com.sofka.hospital.services.PatientService;
import com.sofka.hospital.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping("api/")
public class PatientController {

    @Autowired
    private PatientService patientService;

    private Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;

    @PostMapping("create/patient")
    public ResponseEntity<Response> createPatient(@RequestBody PatientDTO patientDTO){
        response.restart();
        try {
            response.data = patientService.createPatient(patientDTO);
            httpStatus = HttpStatus.CREATED;
        } catch (ConstraintViolationException exception){
            response.data = exception.getCause();
            response.message = exception.getMessage();
            response.error = true;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }
}