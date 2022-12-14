package com.sofka.hospital.controller;

import com.sofka.hospital.dto.MedicalSpecialityDTO;
import com.sofka.hospital.services.MedicalSpecialityService;
import com.sofka.hospital.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
public class MedicalSpecialityController {

    @Autowired
    private MedicalSpecialityService medicalSpecialityService;

    private Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;

    @GetMapping("speciality")
    public List<MedicalSpecialityDTO> getAllSpecialities(){
        return medicalSpecialityService.getAllSpeciality();
    }

    @PostMapping("create/speciality")
    public ResponseEntity<Response> createSpeciality(@RequestBody MedicalSpecialityDTO medicalSpecialityDTO){
        response.restart();
        try {
            response.data = medicalSpecialityService.createSpeciality(medicalSpecialityDTO);
            httpStatus = HttpStatus.CREATED;
        } catch (ConstraintViolationException exception){
            response.data = exception.getCause();
            response.message = exception.getMessage();
            response.error = true;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }

    @DeleteMapping("delete/speciality")
    public ResponseEntity<Response> deleteSpeciality(@RequestBody MedicalSpecialityDTO medicalSpecialityDTO){
        response.restart();
        boolean state = medicalSpecialityService.deleteSpeciality(medicalSpecialityDTO);
        if(state){
            response.data = medicalSpecialityDTO;
            response.message = "successfully deleted";
            httpStatus = HttpStatus.OK;
        } else {
            response.data = medicalSpecialityDTO;
            response.message = "Cannot be deleted";
            httpStatus = HttpStatus.BAD_REQUEST;

        }
        return new ResponseEntity(response, httpStatus);
    }

    @PatchMapping(path = "update/name/speciality")
    public ResponseEntity<Response> updateName(@RequestBody MedicalSpecialityDTO medicalSpecialityDTO){
        response.restart();
        try {
           medicalSpecialityService.updateName(medicalSpecialityDTO.getSpecialityId(), medicalSpecialityDTO.getName());
           response.data = medicalSpecialityDTO.getName();
           response.message = "successfully updated";
           httpStatus = HttpStatus.OK;
       } catch(Exception exception){
           response.data = exception.getCause();
           response.message = exception.getMessage();
           response.error = true;
           httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
       }
        return new ResponseEntity(response, httpStatus);
    }

    @PutMapping(path = "update/speciality")
    public ResponseEntity<Response> updateSpeciality(@RequestBody MedicalSpecialityDTO medicalSpecialityDTO){
        response.restart();
        try {
            medicalSpecialityService.updateSpeciality(medicalSpecialityDTO.getSpecialityId(),
                    medicalSpecialityDTO.getName(), medicalSpecialityDTO.getPhysicianInCharge());
            response.data = medicalSpecialityDTO;
            response.message = "successfully updated";
            httpStatus = HttpStatus.OK;
        } catch(Exception exception){
            response.data = exception.getCause();
            response.message = exception.getMessage();
            response.error = true;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }



}
