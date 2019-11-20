package com.patientservice.patientservice.controllers;


import com.patientservice.patientservice.entity.Patient;
import com.patientservice.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
public class PatientRestController {
    @Autowired
   private PatientRepository patientRepository;
    @Autowired
   private RestTemplate restTemplate;

    @RequestMapping(value = "/Patient-Service/findAll",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List findAllPatienten(){
        return (List)patientRepository.findAll();
    }

    @RequestMapping(value = "/Patient-Service/addPatient",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity savePatient(@RequestBody Patient patient){
     List<Patient> patientenList= (List)patientRepository.findAll();
     for(Patient temp:patientenList){
       if(temp.getName().equalsIgnoreCase(patient.getName()))
           if(temp.getAddress().equalsIgnoreCase(patient.getAddress())){
               return new ResponseEntity(HttpStatus.FOUND);
           }


     }
     return new ResponseEntity(patientRepository.save(patient),HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Patient-Service/getPatientByName/{name}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPatientByHisName(@PathVariable("name")String name){
        Optional<Patient>optionalPatient=patientRepository.findPatientByName(name);
        if(!optionalPatient.isPresent()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
     return new ResponseEntity(optionalPatient.get(),HttpStatus.FOUND);
    }
    @RequestMapping(value = "/Patient-Service/deletePatientById/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePatientById(@PathVariable("id")Long id){
   return patientRepository.findById(id).map(patient -> {
       patientRepository.delete(patient);
       return new ResponseEntity("Patient with Id "+id+" has deleted",HttpStatus.OK);
   }).orElse(new ResponseEntity(HttpStatus.NO_CONTENT));

    }
    @RequestMapping(value = "/Patient-Service/deletePatientByName/{name}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePatientByName(@PathVariable("name")String name){
        return patientRepository.findPatientByName(name).map(patient -> {
            patientRepository.delete(patient);
            return new ResponseEntity("Patient with Name "+name+"has been deleted",HttpStatus.OK);
        }).orElse(new ResponseEntity(HttpStatus.NO_CONTENT));
    }


    @RequestMapping(value = "/Patient-Service/findHospitalByName/{name}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHospitalByName(@PathVariable("name")String name) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

     ResponseEntity<String> responseEntity=  restTemplate.exchange("http://localhost:9092/Hospital-Service/getHospitalByName/"+name,HttpMethod.GET,getHeaders(),String.class);
     return  responseEntity.getBody();
    }
    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
    }

