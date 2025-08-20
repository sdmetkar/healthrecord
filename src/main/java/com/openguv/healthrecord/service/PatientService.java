package com.openguv.healthrecord.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openguv.healthrecord.dto.FilterNode;
import com.openguv.healthrecord.dto.FilterSpecification;
import com.openguv.healthrecord.entity.DiagnosisEntity;
import com.openguv.healthrecord.entity.PatientEntity;
import com.openguv.healthrecord.entity.VisitEntity;
import com.openguv.healthrecord.mapper.PatientMapper;
import com.openguv.healthrecord.model.Patient;
import com.openguv.healthrecord.model.PatientDashboard;
import com.openguv.healthrecord.model.VisitSummary;
import com.openguv.healthrecord.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final ObjectMapper objectMapper;
    private final PatientMapper patientMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, ObjectMapper objectMapper, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.objectMapper = objectMapper;
        this.patientMapper = patientMapper;
    }

    public List<Patient> getAllPatients() {
        return patientMapper.toSimpleDTOList(patientRepository.findAll());
    }

    @Cacheable(value = "patients", key = "#id")
    public Patient getPatientById(Long id) {
        PatientEntity entity = patientRepository.findById(id).orElse(null);
        return entity != null ? patientMapper.toSimpleDTO(entity) : null;
    }

    public Patient createPatient(Patient patient) {
        PatientEntity entity = patientMapper.toEntity(patient);
        return patientMapper.toSimpleDTO(patientRepository.save(entity));
    }

    public Patient updatePatient(Long id, Patient patient) {
        PatientEntity entity = patientRepository.findById(id).orElse(null);
        if (entity == null) return null;
        patientMapper.updateEntityFromModel(patient, entity);
        return patientMapper.toSimpleDTO(patientRepository.save(entity));
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> findPatientsByName(String name) {
        return patientMapper.toSimpleDTOList(patientRepository.findByNameContainingIgnoreCase(name));
    }

    public List<PatientDashboard> getPatientsUsingCriteria(String queryCriteria) {

        try {
            queryCriteria = queryCriteria.replace("\\\"", "\"");
            FilterNode rootFilter = objectMapper.readValue(queryCriteria, FilterNode.class);
            Specification<PatientEntity> spec = new FilterSpecification<>(rootFilter);
            LocalDate now = LocalDate.now();
            return patientRepository.findAll(spec).stream().map(p -> {
                List<VisitEntity> visits = p.getVisits();
                int totalVisits = visits != null ? visits.size() : 0;
                int visitsLastYear = 0;
                List<VisitSummary> visitSummaries = List.of();
                if (visits != null) {
                    visitsLastYear = (int) visits.stream()
                            .filter(v -> v.getDate() != null && v.getDate().toLocalDate().isAfter(now.minusYears(1)))
                            .count();
                    visitSummaries = visits.stream().map(v ->
                            new VisitSummary().date(String.valueOf(v.getDate())).doctorName(v.getDoctor().getName()).diagnoses(v.getDiagnoses().stream().map(DiagnosisEntity::getDescription).collect(Collectors.toList()))
                    ).collect(Collectors.toList());
                }
                return new PatientDashboard()
                        .name(p.getName())
                        .gender(PatientDashboard.GenderEnum.valueOf(p.getGender().name()))
                        .dateOfBirth(p.getDob() != null ? p.getDob().toString() : null)
                        .totalVisits(totalVisits)
                        .visitsLastYear(visitsLastYear)
                        .visits(visitSummaries);
            }).toList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse filter criteria", e);
        }
    }

}
