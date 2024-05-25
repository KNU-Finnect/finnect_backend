package com.finnect.crm.domain.column;

import com.finnect.view.domain.constant.FilterCondition;
import java.util.List;
import lombok.Getter;

public enum DataType{
    DEAL("DEAL"), COMPANY("CCOMPANY");

    @Getter
    private final String type;

    DataType(String type) {
        this.type = type;
    }

    public static DataType getDataType(String dataType){
        for(DataType type : DataType.values()){
            if(type.getType().equals(dataType)){
                return type;
            }
        }
        return null;
    }
}
