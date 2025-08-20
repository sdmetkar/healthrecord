package com.openguv.healthrecord.repository;

import com.openguv.healthrecord.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long>, JpaSpecificationExecutor<PatientEntity> {
    List<PatientEntity> findByNameContainingIgnoreCase(String name);

}
