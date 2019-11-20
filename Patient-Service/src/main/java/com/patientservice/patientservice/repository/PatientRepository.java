package com.patientservice.patientservice.repository;

import com.patientservice.patientservice.entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient,Long> {


    @Query(value = "select * from PATIENT where PATIENT.NAME=:name",nativeQuery = true)
    public Optional<Patient> findPatientByName(@Param("name") String name);
}
