package com.openguv.healthrecord.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(
        name = "visit",
        indexes = {
                @Index(name = "idx_visit_patient_id", columnList = "patient_id"),
                @Index(name = "idx_visit_doctor_id", columnList = "doctor_id"),
                @Index(name = "idx_visit_date", columnList = "date"),
                @Index(name = "idx_visit_patient_date", columnList = "patient_id, date")
        }
)
public class VisitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Patient is required")
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @NotNull(message = "Date Time is required")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;

    @NotNull(message = "Doctor is required")
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @OneToMany(mappedBy = "visitId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @Schema(hidden = true)
    private List<DiagnosisEntity> diagnoses;
    // Getters and setters
}

