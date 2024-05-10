package com.finnect.crm.domain.column;

import lombok.Getter;

public enum ColumnType {
    TEXT("TEXT"), NUMBER("NUMBER");

    @Getter
    private final String type;

    ColumnType(String str) {
        this.type = str;
    }

    public static ColumnType getColumnType(String columnType) {
        for(ColumnType type : ColumnType.values()){
            if(type.getType().equals(columnType)){
                return type;
            }
        }
        return null;
    }
}