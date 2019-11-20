package com.service.hospital.controller;

import com.service.hospital.entity.Hospital;
import com.service.hospital.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class HospitalRestController {
    @Autowired
    HospitalRepository hospitalRepository;

    @RequestMapping(value = "/Hospital-Service/getHospital", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List getHospital() {
        return (List) hospitalRepository.findAll();
    }

    @RequestMapping(value = "/Hospital-Service/saveHospital", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object createHospital(@RequestBody Hospital hospital) {
        List<Hospital> hospitalList = getHospital();
        for (Hospital temp : hospitalList) {
            if (temp.equals(hospital)) {
                return new ResponseEntity(HttpStatus.FOUND);
            }
        }
        return new ResponseEntity(hospitalRepository.save(hospital), HttpStatus.CREATED);

    }
    @RequestMapping(value = "/Hospital-Servic/deleteHospitalById/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteHospital(@PathVariable("id")Long id){
        return hospitalRepository.findById(id).map(hospital -> {

      hospitalRepository.delete(hospital);
      return new ResponseEntity("Hospital Object has been deleted",HttpStatus.OK);

        }).orElse(new ResponseEntity(HttpStatus.NO_CONTENT));
    }
    @RequestMapping(value = "/Hospital-Service/getHospitalByName/{name}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getHospitalByName(@PathVariable("name")String name){
        Optional<Hospital>hospital=hospitalRepository.findHospitalByNameHospital(name);
        if(!hospital.isPresent()){
         return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(hospital.get(),HttpStatus.FOUND);
    }
}