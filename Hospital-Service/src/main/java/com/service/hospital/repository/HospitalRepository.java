package com.service.hospital.repository;

import com.service.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital,Long> {



    public Optional<Hospital>findHospitalByNameHospital(String name);
}
