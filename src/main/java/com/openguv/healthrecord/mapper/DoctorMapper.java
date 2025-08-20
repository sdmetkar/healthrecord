package com.openguv.healthrecord.mapper;

import com.openguv.healthrecord.entity.DoctorEntity;
import com.openguv.healthrecord.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper extends BaseMapper {

    Doctor toModel(DoctorEntity entity);

    List<Doctor> toModelList(List<DoctorEntity> entities);

    DoctorEntity toEntity(Doctor model);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromModel(Doctor model, @MappingTarget DoctorEntity entity);
}
