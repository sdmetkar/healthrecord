package com.openguv.healthrecord.controller;

import com.openguv.healthrecord.api.DoctorControllerApi;
import com.openguv.healthrecord.entity.DoctorEntity;

import com.openguv.healthrecord.model.Doctor;
import com.openguv.healthrecord.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController implements DoctorControllerApi {
    private final DoctorService doctorService;
    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody @Valid Doctor doctor) {
        return ResponseEntity.ok(doctorService.createDoctor(doctor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("id") Long id, @RequestBody @Valid Doctor doctor) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, doctor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("id") Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Doctor>> findDoctorsByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(doctorService.findDoctorsByName(name));
    }
    // Add more endpoints as needed
}
