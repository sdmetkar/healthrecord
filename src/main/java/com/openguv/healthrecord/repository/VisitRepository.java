package com.openguv.healthrecord.repository;

import com.openguv.healthrecord.entity.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, Long> {
    List<VisitEntity> findByPatientId(Long patientId);

    List<VisitEntity> findByDoctorId(Long doctorId);

    List<VisitEntity> findByPatientIdAndDoctorId(Long patientId, Long doctorId);

    List<VisitEntity> findByPatientIdAndDateBetween(Long patientId, LocalDateTime start, LocalDateTime end);

    List<VisitEntity> findByDoctorIdAndDateBetween(Long doctorId, LocalDateTime start, LocalDateTime end);

    List<VisitEntity> findByPatientIdAndDoctorIdAndDateBetween(Long patientId, Long doctorId, LocalDateTime start, LocalDateTime end);

    List<VisitEntity> findByDateBetween(LocalDateTime start, LocalDateTime end);

}
