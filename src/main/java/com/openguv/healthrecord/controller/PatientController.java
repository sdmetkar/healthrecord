package com.openguv.healthrecord.controller;

import com.openguv.healthrecord.api.PatientApiApi;
import com.openguv.healthrecord.model.Patient;
import com.openguv.healthrecord.model.PatientDashboard;
import com.openguv.healthrecord.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@Tag(name = "Patient API")
public class PatientController implements PatientApiApi {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Get all patients")
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @Operation(summary = "Get patient by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @Operation(summary = "Create patient")
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody @Valid Patient patient) {
        return ResponseEntity.ok(patientService.createPatient(patient));
    }

    @Operation(summary = "Update patient")
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Long id, @RequestBody @Valid Patient patient) {
        return ResponseEntity.ok(patientService.updatePatient(id, patient));
    }

    @Operation(summary = "Delete patient")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Search patients by name")
    @GetMapping("/search")
    public ResponseEntity<List<Patient>> findPatientsByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(patientService.findPatientsByName(name));
    }

    @Operation(summary = "Search patients by criteria")
    @PostMapping("/search/criteria")
    public ResponseEntity<List<PatientDashboard>> searchPatientsByCriteria(@RequestBody String queryCriteria) {
        return ResponseEntity.ok(patientService.getPatientsUsingCriteria(queryCriteria));
    }
}
