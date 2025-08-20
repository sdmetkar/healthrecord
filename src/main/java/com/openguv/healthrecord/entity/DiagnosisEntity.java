package com.openguv.healthrecord.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(
        name = "diagnosis",
        indexes = {
                @Index(name = "idx_diagnosis_code", columnList = "code"),
                @Index(name = "idx_diagnosis_description", columnList = "description"),
                @Index(name = "idx_diagnosis_visit_id", columnList = "visit_id")
        }
)
public class DiagnosisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Visit ID is required")
    @Column(name = "visit_id")
    @Positive(message = "Visit ID must be a positive number")
    private Long visitId;

    @NotBlank(message = "Code is required")
    private String code;

    @NotBlank(message = "Description is required")
    private String description;

    // Manually added getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVisitId() {
        return visitId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
