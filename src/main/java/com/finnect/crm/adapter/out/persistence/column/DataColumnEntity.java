package com.finnect.crm.adapter.out.persistence.column;

import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.crm.domain.column.ColumnType;
import com.finnect.crm.domain.column.DataType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "data_column")
public class DataColumnEntity implements DataColumnState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long columnId;

    @Getter
    private Long workspaceId;

    @Getter
    private String columnName;

    @Enumerated(EnumType.STRING)
    @Getter
    private DataType dType;

    @Enumerated(EnumType.STRING)
    @Getter
    private ColumnType columnType;
    @Getter
    private Double columnIndex;
    private Boolean isHided;

    protected DataColumnEntity() {
    }

    @Builder
    public DataColumnEntity(Long columnId, Long workspaceId, String columnName, DataType dType, ColumnType columnType,
                            Double columnIndex, Boolean isHided) {
        this.columnId = columnId;
        this.workspaceId = workspaceId;
        this.columnName = columnName;
        this.dType = dType;
        this.columnType = columnType;
        this.columnIndex = columnIndex;
        this.isHided = isHided;
    }

    @Override
    public Boolean isHided() {
        return this.isHided;
    }

    public static DataColumnEntity toEntity(DataColumnState columnState){
        return DataColumnEntity.builder()
                .columnId(columnState.getColumnId())
                .workspaceId(columnState.getWorkspaceId())
                .columnName(columnState.getColumnName())
                .columnType(columnState.getColumnType())
                .isHided(columnState.isHided())
                .columnIndex(columnState.getColumnIndex())
                .dType(columnState.getDType())
                .build();
    }

    public DataColumn toDomain(){
        return DataColumn.builder()
                .columnId(this.columnId)
                .workspaceId(this.workspaceId)
                .columnName(this.columnName)
                .dType(this.dType)
                .columnType(this.columnType)
                .columnIndex(this.columnIndex)
                .isHided(this.isHided)
                .build();
    }
    @Override
    public String toString() {
        return "DataColumnEntity{" +
                "columnId=" + columnId +
                ", workspaceId=" + workspaceId +
                '}';
    }
}
