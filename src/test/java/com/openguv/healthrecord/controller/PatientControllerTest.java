package com.openguv.healthrecord.controller;

import com.openguv.healthrecord.model.Patient;
import com.openguv.healthrecord.service.PatientService;
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
public class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @Test
    void getAllPatients_ShouldReturnPatients() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");
        when(patientService.getAllPatients()).thenReturn(List.of(patient));

        ResponseEntity<List<Patient>> response = patientController.getAllPatients();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("John Doe", response.getBody().get(0).getName());
    }

    @Test
    void createPatient_ShouldReturnCreatedPatient() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");
        when(patientService.createPatient(org.mockito.ArgumentMatchers.any())).thenReturn(patient);

        ResponseEntity<Patient> response = patientController.createPatient(patient);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    void deletePatient_ShouldReturnOk() {
        ResponseEntity<Void> response = patientController.deletePatient(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }
}