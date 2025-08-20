package com.openguv.healthrecord;

import com.openguv.healthrecord.entity.Gender;
import com.openguv.healthrecord.entity.PatientEntity;
import com.openguv.healthrecord.mapper.PatientMapper;
import com.openguv.healthrecord.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PatientMapperTest {

    @Autowired
    private PatientMapper patientMapper;

    @Test
    void toEntity_ShouldMapPatientToEntity() {
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setDob(LocalDate.of(1990, 1, 1));

        PatientEntity entity = patientMapper.toEntity(patient);

        assertNotNull(entity);
        assertEquals("John Doe", entity.getName());
        assertEquals(LocalDate.of(1990, 1, 1), entity.getDob());
    }

    @Test
    void toModel_ShouldMapEntityToPatient() {
        PatientEntity entity = new PatientEntity();
        entity.setId(1L);
        entity.setName("John Doe");
        entity.setDob(LocalDate.of(1990, 1, 1));
        entity.setGender(Gender.MALE);

        Patient patient = patientMapper.toSimpleDTO(entity);

        assertNotNull(patient);
        assertEquals(1L, patient.getId());
        assertEquals("John Doe", patient.getName());
    }
}