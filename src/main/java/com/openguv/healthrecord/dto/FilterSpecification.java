package com.openguv.healthrecord.dto;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class FilterSpecification<T> implements Specification<T> {

    private final FilterNode filter;

    public FilterSpecification(FilterNode filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return buildPredicate(filter, root, cb);
    }

    private Predicate buildPredicate(FilterNode node, Root<T> root, CriteriaBuilder cb) {
        if (node.getLogic() != null) {
            List<Predicate> childPredicates = new ArrayList<>();

            for (FilterNode child : node.getConditions()) {
                childPredicates.add(buildPredicate(child, root, cb));
            }

            return switch (node.getLogic().toLowerCase()) {
                case "and" -> cb.and(childPredicates.toArray(new Predicate[0]));
                case "or" -> cb.or(childPredicates.toArray(new Predicate[0]));
                case "not" -> cb.not(cb.and(childPredicates.toArray(new Predicate[0])));
                default -> throw new RuntimeException("Unsupported logic: " + node.getLogic());
            };
        } else if (node.getField() != null) {
            // Handle leaf nodes (field + op + value)
            Path<?> path = root.get(node.getField());
            Object value = node.getValue();

            // Handle date string conversion for lt, gt, lte, gte
            if ((node.getOp().equalsIgnoreCase("lt") || node.getOp().equalsIgnoreCase("gt") || node.getOp().equalsIgnoreCase("lte") || node.getOp().equalsIgnoreCase("gte"))
                    && value instanceof String) {
                // Try to parse as LocalDate
                try {
                    value = java.time.LocalDate.parse((String) value);
                    if (path.getJavaType().isAssignableFrom(java.time.LocalDate.class)) {
                        return switch (node.getOp().toLowerCase()) {
                            case "lt" -> cb.lessThan(path.as(java.time.LocalDate.class), (java.time.LocalDate) value);
                            case "gt" ->
                                    cb.greaterThan(path.as(java.time.LocalDate.class), (java.time.LocalDate) value);
                            case "lte" ->
                                    cb.lessThanOrEqualTo(path.as(java.time.LocalDate.class), (java.time.LocalDate) value);
                            case "gte" ->
                                    cb.greaterThanOrEqualTo(path.as(java.time.LocalDate.class), (java.time.LocalDate) value);
                            default -> throw new RuntimeException("Unsupported op: " + node.getOp());
                        };
                    }
                } catch (Exception ignored) {
                }
            }
            return switch (node.getOp().toLowerCase()) {
                case "eq" -> cb.equal(path, value);
                case "ne" -> cb.notEqual(path, value);
                case "gt" -> cb.greaterThan(path.as(Comparable.class), (Comparable) value);
                case "lt" -> cb.lessThan(path.as(Comparable.class), (Comparable) value);
                case "gte" -> cb.greaterThanOrEqualTo(path.as(Comparable.class), (Comparable) value);
                case "lte" -> cb.lessThanOrEqualTo(path.as(Comparable.class), (Comparable) value);
                case "like" -> cb.like(path.as(String.class), "%" + value + "%");
                case "in" -> path.in((List<?>) value);
                default -> throw new RuntimeException("Unsupported op: " + node.getOp());
            };
        } else {
            return null;
        }
    }
}
