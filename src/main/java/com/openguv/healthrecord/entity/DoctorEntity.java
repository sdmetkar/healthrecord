package com.openguv.healthrecord.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(
        name = "doctor",
        indexes = {
                @Index(name = "idx_doctor_name", columnList = "name")
        }
)
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 4, max = 50, message = "Name must be between 4 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters and spaces")
    private String name;

    @NotBlank(message = "Specialty is required")
    @Size(min = 4, max = 50, message = "Speciality must be between 4 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Speciality must contain only letters and spaces")
    private String specialty;
    // Getters and setters
}
