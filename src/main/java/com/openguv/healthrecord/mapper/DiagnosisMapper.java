package com.openguv.healthrecord.mapper;

import com.openguv.healthrecord.entity.DiagnosisEntity;
import com.openguv.healthrecord.model.Diagnosis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiagnosisMapper extends BaseMapper {

    @Mapping(source = "visitId", target = "visitId")
    Diagnosis toModel(DiagnosisEntity entity);

    List<Diagnosis> toModelList(List<DiagnosisEntity> entities);

    @Mapping(source = "visitId", target = "visitId")
    DiagnosisEntity toEntity(Diagnosis model);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromModel(Diagnosis model, @MappingTarget DiagnosisEntity entity);
}
