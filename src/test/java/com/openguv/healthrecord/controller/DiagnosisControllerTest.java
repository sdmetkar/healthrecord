package com.openguv.healthrecord.controller;

import com.openguv.healthrecord.model.Diagnosis;
import com.openguv.healthrecord.service.DiagnosisService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiagnosisControllerTest {

    @Mock
    private DiagnosisService diagnosisService;

    @InjectMocks
    private DiagnosisController diagnosisController;

    @Test
    void getAllDiagnoses_ShouldReturnDiagnoses() {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(1L);
        diagnosis.setCode("J00");
        when(diagnosisService.getAllDiagnoses()).thenReturn(List.of(diagnosis));

        ResponseEntity<List<Diagnosis>> response = diagnosisController.getAllDiagnoses();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("J00", response.getBody().get(0).getCode());
    }

    @Test
    void getDiagnosisById_ShouldReturnDiagnosis() {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(1L);
        diagnosis.setCode("J00");
        when(diagnosisService.getDiagnosisById(1L)).thenReturn(diagnosis);

        ResponseEntity<Diagnosis> response = diagnosisController.getDiagnosisById(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("J00", response.getBody().getCode());
    }

    @Test
    void createDiagnosis_ShouldReturnCreatedDiagnosis() {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(1L);
        diagnosis.setCode("J00");
        when(diagnosisService.createDiagnosis(org.mockito.ArgumentMatchers.any())).thenReturn(diagnosis);

        ResponseEntity<Diagnosis> response = diagnosisController.createDiagnosis(diagnosis);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("J00", response.getBody().getCode());
    }

    @Test
    void updateDiagnosis_ShouldReturnUpdatedDiagnosis() {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(1L);
        diagnosis.setCode("J01");
        when(diagnosisService.updateDiagnosis(org.mockito.ArgumentMatchers.eq(1L), org.mockito.ArgumentMatchers.any())).thenReturn(diagnosis);

        ResponseEntity<Diagnosis> response = diagnosisController.updateDiagnosis(1L, diagnosis);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("J01", response.getBody().getCode());
    }

    @Test
    void deleteDiagnosis_ShouldReturnOk() {
        ResponseEntity<Void> response = diagnosisController.deleteDiagnosis(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }
}