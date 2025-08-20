package com.openguv.healthrecord.repository;

import com.openguv.healthrecord.entity.DiagnosisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<DiagnosisEntity, Long> {
    // Custom query methods can be added here
    List<DiagnosisEntity> findByVisitId(Long visitId);
}
