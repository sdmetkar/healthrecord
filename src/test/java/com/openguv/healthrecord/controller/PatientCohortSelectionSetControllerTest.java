package com.openguv.healthrecord.controller;

import com.openguv.healthrecord.model.PatientCohortSelectionSet;
import com.openguv.healthrecord.service.PatientCohortSelectionSetService;
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
public class PatientCohortSelectionSetControllerTest {

    @Mock
    private PatientCohortSelectionSetService cohortService;

    @InjectMocks
    private PatientCohortSelectionSetController cohortController;

    @Test
    void getAllCohorts_ShouldReturnCohorts() {
        PatientCohortSelectionSet cohort = new PatientCohortSelectionSet();
        cohort.setId(1L);
        cohort.setName("Test Cohort");
        when(cohortService.getAllCohorts()).thenReturn(List.of(cohort));

        ResponseEntity<List<PatientCohortSelectionSet>> response = cohortController.getAllCohorts();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Cohort", response.getBody().get(0).getName());
    }

    @Test
    void getCohortById_ShouldReturnCohort() {
        PatientCohortSelectionSet cohort = new PatientCohortSelectionSet();
        cohort.setId(1L);
        cohort.setName("Test Cohort");
        when(cohortService.getCohortById(1L)).thenReturn(cohort);

        ResponseEntity<PatientCohortSelectionSet> response = cohortController.getCohortById(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Cohort", response.getBody().getName());
    }

    @Test
    void createCohort_ShouldReturnCreatedCohort() {
        PatientCohortSelectionSet cohort = new PatientCohortSelectionSet();
        cohort.setId(1L);
        cohort.setName("Test Cohort");
        when(cohortService.createCohort(org.mockito.ArgumentMatchers.any())).thenReturn(cohort);

        ResponseEntity<PatientCohortSelectionSet> response = cohortController.createCohort(cohort);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Cohort", response.getBody().getName());
    }

    @Test
    void updateCohort_ShouldReturnUpdatedCohort() {
        PatientCohortSelectionSet cohort = new PatientCohortSelectionSet();
        cohort.setId(1L);
        cohort.setName("Updated Cohort");
        when(cohortService.updateCohort(org.mockito.ArgumentMatchers.eq(1L), org.mockito.ArgumentMatchers.any())).thenReturn(cohort);

        ResponseEntity<PatientCohortSelectionSet> response = cohortController.updateCohort(1L, cohort);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated Cohort", response.getBody().getName());
    }

    @Test
    void deleteCohort_ShouldReturnOk() {
        ResponseEntity<Void> response = cohortController.deleteCohort(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }
}