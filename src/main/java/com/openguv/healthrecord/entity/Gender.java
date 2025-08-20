package com.openguv.healthrecord.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    @JsonCreator
    public static Gender fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null; // This allows @NotNull validation to catch it
        }

        for (Gender gender : Gender.values()) {
            if (gender.displayName.equalsIgnoreCase(value) ||
                    gender.name().equalsIgnoreCase(value)) {
                return gender;
            }
        }

        throw new IllegalArgumentException("Invalid gender value: " + value);
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}
