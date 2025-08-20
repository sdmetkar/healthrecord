package com.openguv.healthrecord.controller;

import com.openguv.healthrecord.model.Doctor;
import com.openguv.healthrecord.service.DoctorService;
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
public class DoctorControllerTest {

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    @Test
    void getAllDoctors_ShouldReturnDoctors() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr Smith");
        when(doctorService.getAllDoctors()).thenReturn(List.of(doctor));

        ResponseEntity<List<Doctor>> response = doctorController.getAllDoctors();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Dr Smith", response.getBody().get(0).getName());
    }

    @Test
    void getDoctorById_ShouldReturnDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr Smith");
        when(doctorService.getDoctorById(1L)).thenReturn(doctor);

        ResponseEntity<Doctor> response = doctorController.getDoctorById(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Dr Smith", response.getBody().getName());
    }

    @Test
    void createDoctor_ShouldReturnCreatedDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr Smith");
        when(doctorService.createDoctor(org.mockito.ArgumentMatchers.any())).thenReturn(doctor);

        ResponseEntity<Doctor> response = doctorController.createDoctor(doctor);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Dr Smith", response.getBody().getName());
    }

    @Test
    void updateDoctor_ShouldReturnUpdatedDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr Smith Updated");
        when(doctorService.updateDoctor(org.mockito.ArgumentMatchers.eq(1L), org.mockito.ArgumentMatchers.any())).thenReturn(doctor);

        ResponseEntity<Doctor> response = doctorController.updateDoctor(1L, doctor);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Dr Smith Updated", response.getBody().getName());
    }

    @Test
    void deleteDoctor_ShouldReturnOk() {
        ResponseEntity<Void> response = doctorController.deleteDoctor(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }
}