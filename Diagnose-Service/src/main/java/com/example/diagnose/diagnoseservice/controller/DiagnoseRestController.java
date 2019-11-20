package com.example.diagnose.diagnoseservice.controller;

import com.example.diagnose.diagnoseservice.entity.Diagnose;
import com.example.diagnose.diagnoseservice.repository.DiagnoseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DiagnoseRestController {

    @Autowired
    DiagnoseRepository diagnoseRepository;

    @RequestMapping(value = "/Diagnose-Service/addDiagnose",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveDiagnose(@RequestBody Diagnose diagnose){
        return new ResponseEntity(diagnoseRepository.save(diagnose), HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Diagnose-Service/getDiagnoseById/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDiagnoseById(@PathVariable("id")Long id){
        Optional<Diagnose>diagnoseOptional=diagnoseRepository.findById(id);
        if(!diagnoseOptional.isPresent()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(diagnoseOptional.get(),HttpStatus.FOUND);
    }

   @RequestMapping(value = "/Diagnose-Service/allDiagnose",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List getDiagnose(){
        return (List) diagnoseRepository.findAll();
   }
}
