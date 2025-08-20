package com.openguv.healthrecord.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@Entity
@Table(
        name = "patient_cohort_selection_set",
        indexes = {
                @Index(name = "idx_cohort_name", columnList = "name")
        }
)
public class PatientCohortSelectionSetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Query criteria is required")
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private String queryCriteria; // JSON or custom DSL

    // Getters and setters
}
