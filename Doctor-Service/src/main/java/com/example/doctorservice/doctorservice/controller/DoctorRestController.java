package com.example.doctorservice.doctorservice.controller;

import com.example.doctorservice.doctorservice.model.Doctor;
import com.example.doctorservice.doctorservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DoctorRestController {
    @Autowired
   private DoctorRepository doctorRepository;

    @RequestMapping(value = "Doctor-Service/doctorsList",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List getDoctors(){
        return (List)doctorRepository.findAll();
    }
    @RequestMapping(value = "Doctor-Service/saveDoctor",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createDoctor(@RequestBody Doctor doctor){

        List<Doctor> doctorList=(List)doctorRepository.findAll();
        for(Doctor temp:doctorList){
           if(temp.getDoctorName().equalsIgnoreCase(doctor.getDoctorName())){
               return new ResponseEntity(HttpStatus.FOUND);
           }
        }
        return new ResponseEntity(doctorRepository.save(doctor),HttpStatus.CREATED);
    }
    @RequestMapping(value = "Doctor-Service/getDoctorByName/{name}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Doctor getDoctorByName(@PathVariable("name")String name){
       Optional<Doctor>optionalDoctor= doctorRepository.getDoctorByDoctorName(name);
       if(!optionalDoctor.isPresent()){
           return null;
       }
       return optionalDoctor.get();
    }
}
