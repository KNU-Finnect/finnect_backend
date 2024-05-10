package com.finnect.crm.adapter.out.column.persistence;

import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.cell.state.DataColumnState;
import com.finnect.crm.domain.column.ColumnType;
import com.finnect.crm.domain.column.DataType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity(name = "data_column")
public class DataColumnEntity implements DataColumnState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long columnId;

    private Long workspaceId;

    private String columnName;
    @Enumerated(EnumType.STRING)
    private DataType dType;

    @Enumerated(EnumType.STRING)
    private ColumnType columnType;
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
    public Long getColumnId() {
        return this.columnId;
    }

    @Override
    public Long getWorkspaceId() {
        return this.workspaceId;
    }

    @Override
    public String getColumnName() {
        return null;
    }

    @Override
    public ColumnType getColumnType() {
        return null;
    }

    @Override
    public Double getColumnIndex() {
        return null;
    }

    @Override
    public Boolean isHided() {
        return null;
    }

    @Override
    public DataType getDType() {
        return null;
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
