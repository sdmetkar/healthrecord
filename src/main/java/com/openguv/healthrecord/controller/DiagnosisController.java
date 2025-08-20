package com.openguv.healthrecord.controller;

import com.openguv.healthrecord.api.DiagnosisControllerApi;
import com.openguv.healthrecord.entity.DiagnosisEntity;

import com.openguv.healthrecord.model.Diagnosis;
import com.openguv.healthrecord.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnoses")
public class DiagnosisController implements DiagnosisControllerApi {
    private final DiagnosisService diagnosisService;
    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @GetMapping
    public ResponseEntity<List<Diagnosis>> getAllDiagnoses() {
        return ResponseEntity.ok(diagnosisService.getAllDiagnoses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diagnosis> getDiagnosisById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(diagnosisService.getDiagnosisById(id));
    }

    @PostMapping
    public ResponseEntity<Diagnosis> createDiagnosis(@RequestBody Diagnosis diagnosis) {
        return ResponseEntity.ok(diagnosisService.createDiagnosis(diagnosis));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diagnosis> updateDiagnosis(@PathVariable("id") Long id, @RequestBody Diagnosis diagnosis) {
        return ResponseEntity.ok(diagnosisService.updateDiagnosis(id, diagnosis));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiagnosis(@PathVariable("id") Long id) {
        diagnosisService.deleteDiagnosis(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Find all diagnoses for a specific visit
     *
     * @param visitId the ID of the visit to find diagnoses for
     * @return a list of diagnoses for the specified visit
     */
    @GetMapping("/visit/{visitId}")
    public ResponseEntity<List<Diagnosis>> findDiagnosesByVisitId(@PathVariable("visitId") Long visitId) {
        return ResponseEntity.ok(diagnosisService.findDiagnosesByVisitId(visitId));
    }
}
