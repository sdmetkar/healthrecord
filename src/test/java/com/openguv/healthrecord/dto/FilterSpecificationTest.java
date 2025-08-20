package com.openguv.healthrecord.dto;

import com.openguv.healthrecord.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.*;

public class FilterSpecificationTest {

    @Test
    void constructor_ShouldCreateSpecification() {
        FilterNode filterNode = new FilterNode();
        filterNode.setField("name");
        filterNode.setOp("eq");
        filterNode.setValue("John");

        FilterSpecification<PatientEntity> spec = new FilterSpecification<>(filterNode);

        assertNotNull(spec);
        assertInstanceOf(Specification.class, spec);
    }

    @Test
    void filterNode_ShouldSetAndGetProperties() {
        FilterNode filterNode = new FilterNode();
        filterNode.setField("name");
        filterNode.setOp("eq");
        filterNode.setValue("John");

        assertEquals("name", filterNode.getField());
        assertEquals("eq", filterNode.getOp());
        assertEquals("John", filterNode.getValue());
    }
}