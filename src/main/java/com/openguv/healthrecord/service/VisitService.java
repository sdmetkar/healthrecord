package com.openguv.healthrecord.service;

import com.openguv.healthrecord.entity.VisitEntity;
import com.openguv.healthrecord.mapper.VisitMapper;
import com.openguv.healthrecord.model.Visit;
import com.openguv.healthrecord.model.VisitCreateRequest;
import com.openguv.healthrecord.repository.DoctorRepository;
import com.openguv.healthrecord.repository.PatientRepository;
import com.openguv.healthrecord.repository.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final VisitMapper visitMapper;

    @Autowired
    public VisitService(VisitRepository visitRepository, PatientRepository patientRepository,
                        DoctorRepository doctorRepository, VisitMapper visitMapper) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.visitMapper = visitMapper;
    }

    public List<Visit> getAllVisits() {
        return visitMapper.toDTOList(visitRepository.findAll());
    }

    @Cacheable(value = "visits", key = "#id", unless = "#result == null")
    public Visit getVisitById(Long id) {
        VisitEntity entity = visitRepository.findById(id).orElse(null);
        return entity != null ? visitMapper.toDTO(entity) : null;
    }

    public Visit createVisit(Visit visit) {
        VisitEntity entity = visitMapper.toEntity(visit);
        return visitMapper.toDTO(visitRepository.save(entity));
    }

    public Visit updateVisit(Long id, Visit visitDetails) {
        VisitEntity visit = visitRepository.findById(id).orElse(null);
        if (visit == null) return null;
        visitMapper.updateEntityFromModel(visitDetails, visit);
        return visitMapper.toDTO(visitRepository.save(visit));
    }


    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }

    public List<Visit> findVisitsByPatient(Long patientId) {
        return visitMapper.toDTOList(visitRepository.findByPatientId(patientId));
    }

    public List<Visit> findVisitsByDoctor(Long doctorId) {
        return visitMapper.toDTOList(visitRepository.findByDoctorId(doctorId));
    }

    public List<Visit> findVisitsByPatientAndDoctor(Long patientId, Long doctorId) {
        return visitMapper.toDTOList(visitRepository.findByPatientIdAndDoctorId(patientId, doctorId));
    }

    public List<Visit>
    findVisitsByPatientAndDateRange(Long patientId, LocalDateTime start, LocalDateTime end) {
        return visitMapper.toDTOList(visitRepository.findByPatientIdAndDateBetween(patientId, start, end));
    }

    public List<Visit> findVisitsByDoctorAndDateRange(Long doctorId, LocalDateTime start, LocalDateTime end) {
        return visitMapper.toDTOList(visitRepository.findByDoctorIdAndDateBetween(doctorId, start, end));
    }

    public List<Visit> findVisitsByPatientDoctorAndDateRange(Long patientId, Long doctorId, LocalDateTime start, LocalDateTime end) {
        return visitMapper.toDTOList(visitRepository.findByPatientIdAndDoctorIdAndDateBetween(patientId, doctorId, start, end));
    }

    public List<Visit> findVisitsByDateRange(LocalDateTime start, LocalDateTime end) {
        return visitMapper.toDTOList(visitRepository.findByDateBetween(start, end));
    }

    public Visit createVisitFromRequest(VisitCreateRequest request) {
        VisitEntity entity = new VisitEntity();
        entity.setPatient(patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient with ID " + request.getPatientId() + " not found")));
        entity.setDoctor(doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor with ID " + request.getDoctorId() + " not found")));
        entity.setDate(request.getDate());
        return visitMapper.toDTO(visitRepository.save(entity));
    }

}
