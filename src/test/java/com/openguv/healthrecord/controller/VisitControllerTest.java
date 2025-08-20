package com.openguv.healthrecord.controller;

import com.openguv.healthrecord.mapper.VisitMapper;
import com.openguv.healthrecord.model.Visit;
import com.openguv.healthrecord.service.VisitService;
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
public class VisitControllerTest {

    @Mock
    private VisitService visitService;

    @Mock
    private VisitMapper visitMapper;

    @InjectMocks
    private VisitController visitController;

    @Test
    void getAllVisits_ShouldReturnVisits() {
        Visit visit = new Visit();
        visit.setId(1L);
        when(visitService.getAllVisits()).thenReturn(List.of(visit));

        ResponseEntity<List<Visit>> response = visitController.getAllVisits();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(1L, response.getBody().get(0).getId());
    }

    @Test
    void getVisitById_ShouldReturnVisit() {
        Visit visit = new Visit();
        visit.setId(1L);
        when(visitService.getVisitById(1L)).thenReturn(visit);

        ResponseEntity<Visit> response = visitController.getVisitById(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void deleteVisit_ShouldReturnOk() {
        ResponseEntity<Void> response = visitController.deleteVisit(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }
}