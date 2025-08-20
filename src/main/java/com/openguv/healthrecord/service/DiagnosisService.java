package com.openguv.healthrecord.service;

import com.openguv.healthrecord.entity.DiagnosisEntity;
import com.openguv.healthrecord.mapper.DiagnosisMapper;
import com.openguv.healthrecord.model.Diagnosis;
import com.openguv.healthrecord.repository.DiagnosisRepository;
import com.openguv.healthrecord.repository.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;
    private final VisitRepository visitRepository;
    private final DiagnosisMapper diagnosisMapper;

    @Autowired
    public DiagnosisService(DiagnosisRepository diagnosisRepository, VisitRepository visitRepository, DiagnosisMapper diagnosisMapper) {
        this.diagnosisRepository = diagnosisRepository;
        this.visitRepository = visitRepository;
        this.diagnosisMapper = diagnosisMapper;
    }

    public List<Diagnosis> getAllDiagnoses() {
        return diagnosisMapper.toModelList(diagnosisRepository.findAll());
    }

    @Cacheable(value = "diagnoses", key = "#id")
    public Diagnosis getDiagnosisById(Long id) {
        DiagnosisEntity entity = diagnosisRepository.findById(id).orElse(null);
        return entity != null ? diagnosisMapper.toModel(entity) : null;
    }

    public Diagnosis createDiagnosis(Diagnosis diagnosis) {
        DiagnosisEntity entity = diagnosisMapper.toEntity(diagnosis);
        validateVisitExists(entity.getVisitId());
        return diagnosisMapper.toModel(diagnosisRepository.save(entity));
    }

    public Diagnosis updateDiagnosis(Long id, Diagnosis diagnosisDetails) {
        DiagnosisEntity diagnosis = diagnosisRepository.findById(id).orElse(null);
        if (diagnosis == null) return null;

        DiagnosisEntity detailsEntity = diagnosisMapper.toEntity(diagnosisDetails);
        validateVisitExists(detailsEntity.getVisitId());

        diagnosisMapper.updateEntityFromModel(diagnosisDetails, diagnosis);
        return diagnosisMapper.toModel(diagnosisRepository.save(diagnosis));
    }

    public void deleteDiagnosis(Long id) {
        diagnosisRepository.deleteById(id);
    }

    /**
     * Validates that a visit with the given ID exists
     *
     * @param visitId the visit ID to validate
     * @throws EntityNotFoundException if no visit exists with the given ID
     */
    private void validateVisitExists(Long visitId) {
        if (!visitRepository.existsById(visitId)) {
            throw new EntityNotFoundException("Visit with ID " + visitId + " does not exist");
        }
    }

    /**
     * Find all diagnoses for a specific visit
     *
     * @param visitId the ID of the visit
     * @return a list of diagnoses for the specified visit
     */
    public List<Diagnosis> findDiagnosesByVisitId(Long visitId) {
        return diagnosisMapper.toModelList(diagnosisRepository.findByVisitId(visitId));
    }
}
