package com.openguv.healthrecord.mapper;

import com.openguv.healthrecord.entity.PatientEntity;
import com.openguv.healthrecord.model.Patient;
import com.openguv.healthrecord.model.PatientDashboard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface PatientMapper extends BaseMapper {

    // For model to entity conversion, use custom mapping method
    PatientEntity toEntity(Patient model);

    // Define the mapping method explicitly to handle the conversion
    default java.time.LocalDate mapStringToDob(String dobString) {
        return dobString != null ? java.time.LocalDate.parse(dobString) : null;
    }

    @Mapping(target = "id", ignore = true)
    void updateEntityFromModel(Patient model, @MappingTarget PatientEntity entity);

    // For DTOs
    @Mapping(target = "visits", ignore = true)
    PatientDashboard toDTO(PatientEntity entity);

    List<PatientDashboard> toDTOList(List<PatientEntity> entities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    Patient toSimpleDTO(PatientEntity entity);

    List<Patient> toSimpleDTOList(List<PatientEntity> entity);

}
