package com.openguv.healthrecord.repository;

import com.openguv.healthrecord.entity.PatientCohortSelectionSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientCohortSelectionSetRepository extends JpaRepository<PatientCohortSelectionSetEntity, Long> {
    // Custom query methods can be added here
}

