package com.finnect.crm.domain.column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.finnect.view.domain.constant.FilterCondition;
import java.util.List;
import lombok.Getter;
@JsonFormat(shape = Shape.OBJECT)
public enum ColumnType {
    TEXT("TEXT", List.of(
            FilterCondition.IS,
            FilterCondition.CONTAINS,
            FilterCondition.NOT_CONTAINS,
            FilterCondition.STARTS_WITH,
            FilterCondition.ENDS_WITH
    )),
    NUMBER("NUMBER", List.of(
            FilterCondition.EQUAL,
            FilterCondition.NOT_EQUAL,
            FilterCondition.LESS_THAN,
            FilterCondition.GREATER_THAN,
            FilterCondition.LESS_THAN_OR_EQUAL,
            FilterCondition.GREATER_THAN_OR_EQUAL
    )),
    DATE("DATE", List.of(
            FilterCondition.IS,
            FilterCondition.BEFORE,
            FilterCondition.AFTER,
            FilterCondition.STARTS_BEFORE,
            FilterCondition.STARTS_AFTER,
            FilterCondition.END_BEFORE,
            FilterCondition.ENDS_AFTER,
            FilterCondition.BETWEEN
    )),
    STATUS("STATUS", List.of(
            FilterCondition.IS,
            FilterCondition.NOT_EQUAL
    )),
    SELECT("SELECT", List.of(
            FilterCondition.IS,
            FilterCondition.NOT_EQUAL
    )),
    MULTI_SELECT("MULTI-SELECT", List.of(
            FilterCondition.IN,
            FilterCondition.NOT_IN
    )),
    CURRENCY("CURRENCY", List.of(
            FilterCondition.EQUAL,
            FilterCondition.NOT_EQUAL,
            FilterCondition.LESS_THAN,
            FilterCondition.GREATER_THAN,
            FilterCondition.LESS_THAN_OR_EQUAL,
            FilterCondition.GREATER_THAN_OR_EQUAL
    )),
    RATING("RATING", List.of(
            FilterCondition.EQUAL,
            FilterCondition.LESS_THAN,
            FilterCondition.GREATER_THAN,
            FilterCondition.LESS_THAN_OR_EQUAL,
            FilterCondition.GREATER_THAN_OR_EQUAL,
            FilterCondition.NOT_EQUAL
    )),
    CHECKBOX("CHECKBOX", List.of(
            FilterCondition.TRUE,
            FilterCondition.FALSE
    )),
    URL("URL", List.of(
            FilterCondition.EQUAL,
            FilterCondition.NOT_EQUAL,
            FilterCondition.CONTAINS,
            FilterCondition.NOT_CONTAINS,
            FilterCondition.STARTS_WITH,
            FilterCondition.ENDS_WITH
    )),
    PARTNER("PARTNER", List.of(
            FilterCondition.IS,
            FilterCondition.NOT_EQUAL
    )),
    PERSON("PERSON", List.of(
            FilterCondition.IS,
            FilterCondition.NOT_EQUAL
    ));

    @Getter
    private final String type;
    @Getter
    private final List<FilterCondition> filterConditions;

    ColumnType(String type, List<FilterCondition> conditions) {
        this.type = type;
        this.filterConditions = conditions;
    }

    public static ColumnType getColumnType(String columnType) {
        for (ColumnType type : ColumnType.values()) {
            if (type.getType().equals(columnType)) {
                return type;
            }
        }
        return null;
    }
}