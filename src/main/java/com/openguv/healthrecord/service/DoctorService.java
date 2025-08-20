package com.openguv.healthrecord.service;

import com.openguv.healthrecord.entity.DoctorEntity;
import com.openguv.healthrecord.mapper.DoctorMapper;
import com.openguv.healthrecord.model.Doctor;
import com.openguv.healthrecord.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    public List<Doctor> getAllDoctors() {
        return doctorMapper.toModelList(doctorRepository.findAll());
    }

    @Cacheable(value = "doctors", key = "#id")
    public Doctor getDoctorById(Long id) {
        DoctorEntity entity = doctorRepository.findById(id).orElse(null);
        return entity != null ? doctorMapper.toModel(entity) : null;
    }

    public Doctor createDoctor(Doctor doctor) {
        DoctorEntity entity = doctorMapper.toEntity(doctor);
        return doctorMapper.toModel(doctorRepository.save(entity));
    }

    public Doctor updateDoctor(Long id, Doctor doctor) {
        DoctorEntity entity = doctorRepository.findById(id).orElse(null);
        if (entity == null) return null;
        doctorMapper.updateEntityFromModel(doctor, entity);
        return doctorMapper.toModel(doctorRepository.save(entity));
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public List<Doctor> findDoctorsByName(String name) {
        return doctorMapper.toModelList(doctorRepository.findByNameContainingIgnoreCase(name));
    }
}
