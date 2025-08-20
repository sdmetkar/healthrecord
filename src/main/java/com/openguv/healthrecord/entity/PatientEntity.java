package com.openguv.healthrecord.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(
        name = "patient",
        indexes = {
                @Index(name = "idx_patient_name", columnList = "name"),
                @Index(name = "idx_patient_gender", columnList = "gender"),
                @Index(name = "idx_patient_dob", columnList = "dob")
        }
)
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 4, max = 50, message = "Name must be between 4 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters and spaces")
    private String name;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dob;

    @NotNull(message = "Gender is required")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @Schema(hidden = true)
    private List<VisitEntity> visits;
    // Getters and setters
}
