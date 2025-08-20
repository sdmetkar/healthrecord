package com.openguv.healthrecord.mapper;

import com.openguv.healthrecord.entity.DoctorEntity;
import com.openguv.healthrecord.entity.PatientEntity;
import com.openguv.healthrecord.entity.VisitEntity;
import com.openguv.healthrecord.model.Visit;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VisitMapperTest {

    private final VisitMapperImpl visitMapper = new VisitMapperImpl();

    @Test
    void toDTO_ShouldMapEntityToVisit() {
        PatientEntity patient = new PatientEntity();
        patient.setId(1L);
        patient.setName("John Doe");

        DoctorEntity doctor = new DoctorEntity();
        doctor.setId(1L);
        doctor.setName("Dr Smith");

        VisitEntity entity = new VisitEntity();
        entity.setId(1L);
        entity.setPatient(patient);
        entity.setDoctor(doctor);
        entity.setDate(LocalDateTime.of(2023, 1, 1, 10, 0));

        Visit visit = visitMapper.toDTO(entity);

        assertNotNull(visit);
        assertEquals(1L, visit.getId());
        assertNotNull(visit.getPatient());
        assertNotNull(visit.getDoctor());
    }
}