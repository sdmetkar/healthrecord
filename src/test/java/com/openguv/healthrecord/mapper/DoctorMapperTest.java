package com.openguv.healthrecord.mapper;

import com.openguv.healthrecord.entity.DoctorEntity;
import com.openguv.healthrecord.model.Doctor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorMapperTest {

    private final DoctorMapperImpl doctorMapper = new DoctorMapperImpl();

    @Test
    void toEntity_ShouldMapDoctorToEntity() {
        Doctor doctor = new Doctor();
        doctor.setName("Dr Smith");
        doctor.setSpecialty("Cardiology");

        DoctorEntity entity = doctorMapper.toEntity(doctor);

        assertNotNull(entity);
        assertEquals("Dr Smith", entity.getName());
        assertEquals("Cardiology", entity.getSpecialty());
    }

    @Test
    void toModel_ShouldMapEntityToDoctor() {
        DoctorEntity entity = new DoctorEntity();
        entity.setId(1L);
        entity.setName("Dr Smith");
        entity.setSpecialty("Cardiology");

        Doctor doctor = doctorMapper.toModel(entity);

        assertNotNull(doctor);
        assertEquals(1L, doctor.getId());
        assertEquals("Dr Smith", doctor.getName());
        assertEquals("Cardiology", doctor.getSpecialty());
    }
}