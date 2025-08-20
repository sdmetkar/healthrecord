package com.openguv.healthrecord.mapper;

import org.mapstruct.MapperConfig;

import java.time.LocalDate;
import java.time.LocalDateTime;

@MapperConfig
public interface BaseMapper {


    /**
     * Converts LocalDateTime to String
     */
    default String mapLocalDateTimeToString(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.toString() : null;
    }

    /**
     * Converts LocalDate to String
     */
    default String mapLocalDateToString(LocalDate date) {
        return date != null ? date.toString() : null;
    }

    /**
     * Converts String to LocalDateTime (for model to entity conversion)
     */
    default LocalDateTime mapToLocalDateTime(String dateTimeString) {
        try {
            return dateTimeString != null ? LocalDateTime.parse(dateTimeString) : null;
        } catch (Exception e) {
            return null;
        }
    }
}
