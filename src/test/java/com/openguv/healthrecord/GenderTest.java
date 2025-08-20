package com.openguv.healthrecord;

import com.openguv.healthrecord.entity.Gender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GenderTest {

    @Test
    void fromString_ShouldReturnCorrectGender() {
        assertEquals(Gender.MALE, Gender.fromString("Male"));
        assertEquals(Gender.FEMALE, Gender.fromString("Female"));
        assertEquals(Gender.OTHER, Gender.fromString("Other"));
    }

    @Test
    void fromString_ShouldBeCaseInsensitive() {
        assertEquals(Gender.MALE, Gender.fromString("MALE"));
        assertEquals(Gender.FEMALE, Gender.fromString("female"));
    }

    @Test
    void fromString_WithInvalidValue_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Gender.fromString("Invalid"));
    }

    @Test
    void getDisplayName_ShouldReturnCorrectName() {
        assertEquals("Male", Gender.MALE.getDisplayName());
        assertEquals("Female", Gender.FEMALE.getDisplayName());
        assertEquals("Other", Gender.OTHER.getDisplayName());
    }
}