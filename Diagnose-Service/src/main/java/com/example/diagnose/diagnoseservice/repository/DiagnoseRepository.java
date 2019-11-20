package com.example.diagnose.diagnoseservice.repository;

import com.example.diagnose.diagnoseservice.entity.Diagnose;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnoseRepository extends CrudRepository<Diagnose,Long> {
}
