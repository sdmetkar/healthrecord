package com.openguv.healthrecord.mapper;

import com.openguv.healthrecord.entity.DoctorEntity;
import com.openguv.healthrecord.entity.PatientEntity;
import com.openguv.healthrecord.entity.VisitEntity;
import com.openguv.healthrecord.model.Doctor;
import com.openguv.healthrecord.model.Patient;
import com.openguv.healthrecord.model.Visit;
import com.openguv.healthrecord.model.VisitSummary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VisitMapper extends BaseMapper {

    List<Visit> toModelList(List<VisitEntity> entities);

    @Mapping(target = "patient", source = "patient")
    @Mapping(target = "doctor", source = "doctor")
    @Mapping(target = "date", source = "model.date")
    VisitEntity toEntity(Visit model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "date", source = "model.date")
    void updateEntityFromModel(Visit model, @MappingTarget VisitEntity entity);

    // DTO mappings with simplified references
    @Mapping(target = "patient", source = "patient", qualifiedByName = "patientToPatientModel")
    @Mapping(target = "doctor", source = "doctor", qualifiedByName = "doctorToDoctorModel")
    @Mapping(target = "date", source = "entity.date")
    Visit toDTO(VisitEntity entity);

    List<Visit> toDTOList(List<VisitEntity> entities);

    // Visit summary for dashboard
    @Mapping(target = "visitId", source = "id")
    @Mapping(target = "date", source = "entity.date")
    @Mapping(target = "doctorName", source = "doctor.name")
    @Mapping(target = "diagnoses", expression = "java(entity.getDiagnoses().stream().map(d -> d.getDescription()).toList())")
    VisitSummary toSummary(VisitEntity entity);

    List<VisitSummary> toSummaryList(List<VisitEntity> entities);

    // Helper methods with named qualifiers to avoid ambiguity
    @Named("patientToPatientModel")
    default Patient patientToPatientModel(PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }
        Patient patient = new Patient();
        patient.setId(patientEntity.getId());
        patient.setName(patientEntity.getName());
        if (patientEntity.getDob() != null) {
            patient.setDob(patientEntity.getDob());
        }
        return patient;
    }

    @Named("doctorToDoctorModel")
    default Doctor doctorToDoctorModel(DoctorEntity doctorEntity) {
        if (doctorEntity == null) {
            return null;
        }
        Doctor doctor = new Doctor();
        doctor.setId(doctorEntity.getId());
        doctor.setName(doctorEntity.getName());
        doctor.setSpecialty(doctorEntity.getSpecialty());
        return doctor;
    }
}
