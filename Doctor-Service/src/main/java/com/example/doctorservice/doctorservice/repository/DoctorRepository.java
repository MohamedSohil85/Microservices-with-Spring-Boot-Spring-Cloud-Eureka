package com.example.doctorservice.doctorservice.repository;

import com.example.doctorservice.doctorservice.model.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,Long> {

    public Optional<Doctor> getDoctorByDoctorName(String name);
}
