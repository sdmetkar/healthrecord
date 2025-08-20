package com.openguv.healthrecord.controller;

import com.openguv.healthrecord.api.PatientCohortSelectionSetControllerApi;
import com.openguv.healthrecord.model.PatientCohortSelectionSet;
import com.openguv.healthrecord.model.PatientDashboard;
import com.openguv.healthrecord.service.PatientCohortSelectionSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cohorts")
public class PatientCohortSelectionSetController implements PatientCohortSelectionSetControllerApi {
    private final PatientCohortSelectionSetService cohortService;

    @Autowired
    public PatientCohortSelectionSetController(PatientCohortSelectionSetService cohortService) {
        this.cohortService = cohortService;
    }

    @GetMapping
    public ResponseEntity<List<PatientCohortSelectionSet>> getAllCohorts() {
        return ResponseEntity.ok(cohortService.getAllCohorts());
    }

    @GetMapping("/{id}/patients")
    public ResponseEntity<List<PatientDashboard>> getPatientsForCohort(@PathVariable("id") Long cohortId) {
        return ResponseEntity.ok(cohortService.getPatientsForCohort(cohortId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientCohortSelectionSet> getCohortById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(cohortService.getCohortById(id));
    }

    @PostMapping
    public ResponseEntity<PatientCohortSelectionSet> createCohort(@RequestBody PatientCohortSelectionSet cohort) {
        return ResponseEntity.ok(cohortService.createCohort(cohort));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientCohortSelectionSet> updateCohort(@PathVariable("id") Long id, @RequestBody PatientCohortSelectionSet cohort) {
        return ResponseEntity.ok(cohortService.updateCohort(id, cohort));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCohort(@PathVariable("id") Long id) {
        cohortService.deleteCohort(id);
        return ResponseEntity.ok().build();
    }
}
