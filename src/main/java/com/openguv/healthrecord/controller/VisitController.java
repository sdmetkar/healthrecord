package com.openguv.healthrecord.controller;

import com.openguv.healthrecord.api.VisitApiApi;
import com.openguv.healthrecord.model.Visit;
import com.openguv.healthrecord.model.VisitCreateRequest;
import com.openguv.healthrecord.service.VisitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitController implements VisitApiApi {
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    private static LocalDateTime toStartOfDay(LocalDate date) {
        return date != null ? date.atStartOfDay() : null;
    }

    private static LocalDateTime toEndOfDay(LocalDate date) {
        return date != null ? date.atTime(23, 59, 59) : null;
    }

    @GetMapping
    public ResponseEntity<List<Visit>> getAllVisits() {
        return ResponseEntity.ok(visitService.getAllVisits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visit> getVisitById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(visitService.getVisitById(id));
    }

    @PostMapping
    public ResponseEntity<Visit> createVisit(@RequestBody @Valid VisitCreateRequest request) {
        return ResponseEntity.ok(visitService.createVisitFromRequest(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visit> updateVisit(@PathVariable("id") Long id, @RequestBody @Valid Visit visit) {
        Visit dto = visitService.getVisitById(id);
        return ResponseEntity.ok(visitService.updateVisit(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable("id") Long id) {
        visitService.deleteVisit(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Find visits by patient ID
     *
     * @param patientId ID of the patient
     * @return List of visits for the specified patient
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Visit>> getVisitsByPatient(@PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok(visitService.findVisitsByPatient(patientId));
    }

    /**
     * Find visits by doctor ID
     *
     * @param doctorId ID of the doctor
     * @return List of visits for the specified doctor
     */
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Visit>> getVisitsByDoctor(@PathVariable("doctorId") Long doctorId) {
        return ResponseEntity.ok(visitService.findVisitsByDoctor(doctorId));
    }

    /**
     * Find visits by both patient ID and doctor ID
     *
     * @param patientId ID of the patient
     * @param doctorId  ID of the doctor
     * @return List of visits for the specified patient and doctor combination
     */
    @GetMapping("/patient/{patientId}/doctor/{doctorId}")
    public ResponseEntity<List<Visit>> getVisitsByPatientAndDoctor(
            @PathVariable("patientId") Long patientId,
            @PathVariable("doctorId") Long doctorId) {
        return ResponseEntity.ok(visitService.findVisitsByPatientAndDoctor(patientId, doctorId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Visit>> searchVisits(
            @RequestParam(value = "patientId", required = false) Long patientId,
            @RequestParam(value = "doctorId", required = false) Long doctorId,
            @RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        List<Visit> visits;
        LocalDateTime startDateTime = toStartOfDay(start);
        LocalDateTime endDateTime = toEndOfDay(end);

        if (patientId != null && doctorId != null && start != null && end != null) {
            visits = visitService.findVisitsByPatientDoctorAndDateRange(patientId, doctorId, startDateTime, endDateTime);
        } else if (patientId != null && doctorId != null) {
            visits = visitService.findVisitsByPatientAndDoctor(patientId, doctorId);
        } else if (patientId != null && start != null && end != null) {
            visits = visitService.findVisitsByPatientAndDateRange(patientId, startDateTime, endDateTime);
        } else if (doctorId != null && start != null && end != null) {
            visits = visitService.findVisitsByDoctorAndDateRange(doctorId, startDateTime, endDateTime);
        } else if (patientId != null) {
            visits = visitService.findVisitsByPatient(patientId);
        } else if (doctorId != null) {
            visits = visitService.findVisitsByDoctor(doctorId);
        } else if (start != null && end != null) {
            visits = visitService.findVisitsByDateRange(startDateTime, endDateTime);
        } else {
            throw new IllegalArgumentException("At least one search parameter must be provided");
        }

        return ResponseEntity.ok(visits);
    }
}
