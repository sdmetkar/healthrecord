package com.openguv.healthrecord.dto;

import java.util.List;

public class FilterNode {
    // For leaf conditions
    private String field;
    private String op;     // "eq", "gt", "lt", etc.
    private Object value;

    // For logic group nodes
    private String logic;  // "and", "or", "not"
    private List<FilterNode> conditions;

    // Getters and setters
    public String getField() { return field; }
    public void setField(String field) { this.field = field; }

    public String getOp() { return op; }
    public void setOp(String op) { this.op = op; }

    public Object getValue() { return value; }
    public void setValue(Object value) { this.value = value; }

    public String getLogic() { return logic; }
    public void setLogic(String logic) { this.logic = logic; }

    public List<FilterNode> getConditions() { return conditions; }
    public void setConditions(List<FilterNode> conditions) { this.conditions = conditions; }
}
