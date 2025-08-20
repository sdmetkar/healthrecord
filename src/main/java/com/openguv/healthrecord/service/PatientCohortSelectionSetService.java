package com.openguv.healthrecord.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openguv.healthrecord.dto.FilterNode;
import com.openguv.healthrecord.dto.FilterSpecification;
import com.openguv.healthrecord.entity.DiagnosisEntity;
import com.openguv.healthrecord.entity.PatientCohortSelectionSetEntity;
import com.openguv.healthrecord.entity.PatientEntity;
import com.openguv.healthrecord.entity.VisitEntity;
import com.openguv.healthrecord.mapper.PatientCohortSelectionSetMapper;
import com.openguv.healthrecord.model.PatientCohortSelectionSet;
import com.openguv.healthrecord.model.PatientDashboard;
import com.openguv.healthrecord.model.VisitSummary;
import com.openguv.healthrecord.repository.PatientCohortSelectionSetRepository;
import com.openguv.healthrecord.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientCohortSelectionSetService {
    private final PatientCohortSelectionSetRepository cohortRepository;
    private final PatientRepository patientRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private PatientCohortSelectionSetMapper mapper;

    @Autowired
    public PatientCohortSelectionSetService(PatientCohortSelectionSetRepository cohortRepository, PatientRepository patientRepository) {
        this.cohortRepository = cohortRepository;
        this.patientRepository = patientRepository;
    }

    public List<PatientCohortSelectionSet> getAllCohorts() {
        return mapper.toModelList(cohortRepository.findAll());
    }

    public List<PatientDashboard> getPatientsForCohort(Long cohortId) {
        PatientCohortSelectionSetEntity cohort = cohortRepository.findById(cohortId).orElse(null);
        if (cohort == null) return List.of();

        try {
            FilterNode rootFilter = objectMapper.readValue(cohort.getQueryCriteria(), FilterNode.class);
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

    public PatientCohortSelectionSet getCohortById(Long id) {
        return mapper.toModel(cohortRepository.findById(id).orElse(null));
    }

    public PatientCohortSelectionSet createCohort(PatientCohortSelectionSet cohort) {
        return mapper.toModel(cohortRepository.save(mapper.toEntity(cohort)));
    }

    public PatientCohortSelectionSet updateCohort(Long id, PatientCohortSelectionSet cohortDetails) {
        PatientCohortSelectionSetEntity cohort = cohortRepository.findById(id).orElse(null);
        if (cohort == null) return null;
        cohort.setName(cohortDetails.getName());
        cohort.setDescription(cohortDetails.getDescription());
        cohort.setQueryCriteria(cohortDetails.getQueryCriteria());
        return mapper.toModel(cohortRepository.save(cohort));
    }

    public void deleteCohort(Long id) {
        cohortRepository.deleteById(id);
    }


}
