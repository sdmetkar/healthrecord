package com.openguv.healthrecord.mapper;

import com.openguv.healthrecord.entity.PatientCohortSelectionSetEntity;
import com.openguv.healthrecord.model.PatientCohortSelectionSet;
import com.openguv.healthrecord.model.PatientDashboard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientCohortSelectionSetMapper extends BaseMapper {

    PatientCohortSelectionSet toModel(PatientCohortSelectionSetEntity entity);

    List<PatientCohortSelectionSet> toModelList(List<PatientCohortSelectionSetEntity> entities);

    PatientCohortSelectionSetEntity toEntity(PatientCohortSelectionSet model);

    List<PatientDashboard> toDTOList(List<PatientDashboard> patientDashboards);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromModel(PatientCohortSelectionSet model, @MappingTarget PatientCohortSelectionSetEntity entity);
}
