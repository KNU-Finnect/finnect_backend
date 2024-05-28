package com.finnect.view.domain.constant;

import lombok.Getter;

public enum FilterCondition {
    // Common conditions
    EQUAL(" = "),
    NOT_EQUAL(" != "),
    LESS_THAN(" < "),
    GREATER_THAN(" > "),
    LESS_THAN_OR_EQUAL(" <= "),
    GREATER_THAN_OR_EQUAL(" >= "),
    CONTAINS(" LIKE "),
    NOT_CONTAINS(" NOT LIKE "),
    STARTS_WITH(" LIKE "),
    ENDS_WITH(" LIKE "),
    IN(" IN "),
    NOT_IN(" NOT IN "),
    IS_NULL(" IS NULL "),
    IS_NOT_NULL(" IS NOT NULL "),
    BETWEEN(" BETWEEN "),
    TRUE(" = TRUE "),
    FALSE(" = FALSE "),

    // Specific to certain types
    IS(" = "), // Used for DATE, STATUS, SELECT, PARTNER, PERSON
    BEFORE(" < "), // Specific to DATE
    AFTER(" > "), // Specific to DATE
    STARTS_BEFORE(" <= "), // Specific to DATE
    STARTS_AFTER(" >= "), // Specific to DATE
    END_BEFORE(" <= "), // Specific to DATE
    ENDS_AFTER(" >= "); // Specific to DATE


    @Getter
    private final String operator;

    FilterCondition(String operator) {
        this.operator = operator;
    }

}
