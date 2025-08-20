package com.openguv.healthrecord;

import com.openguv.healthrecord.entity.DiagnosisEntity;
import com.openguv.healthrecord.mapper.DiagnosisMapper;
import com.openguv.healthrecord.model.Diagnosis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DiagnosisMapperTest {

    @Autowired
    private DiagnosisMapper diagnosisMapper;

    @Test
    void toEntity_ShouldMapDiagnosisToEntity() {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setCode("J00");
        diagnosis.setDescription("Common Cold");
        diagnosis.setVisitId(1L);

        DiagnosisEntity entity = diagnosisMapper.toEntity(diagnosis);

        assertNotNull(entity);
        assertEquals("J00", entity.getCode());
        assertEquals("Common Cold", entity.getDescription());
        assertEquals(1L, entity.getVisitId());
    }

    @Test
    void toModel_ShouldMapEntityToDiagnosis() {
        DiagnosisEntity entity = new DiagnosisEntity();
        entity.setId(1L);
        entity.setCode("J00");
        entity.setDescription("Common Cold");
        entity.setVisitId(1L);

        Diagnosis diagnosis = diagnosisMapper.toModel(entity);

        assertNotNull(diagnosis);
        assertEquals(1L, diagnosis.getId());
        assertEquals("J00", diagnosis.getCode());
        assertEquals("Common Cold", diagnosis.getDescription());
        assertEquals(1L, diagnosis.getVisitId());
    }
}