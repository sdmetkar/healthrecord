package com.openguv.healthrecord.repository;

import com.openguv.healthrecord.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    List<DoctorEntity> findByNameContainingIgnoreCase(String name);
}
