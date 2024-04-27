package com.finnect.cell.adapter.out.persistence;


import com.finnect.cell.domain.DataRow;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "data_row")
public class DataRowEntity implements com.finnect.cell.domain.state.DataRowState {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataRowId;
    public DataRowEntity() {}
    public DataRowEntity(Long dataRowId) {
        this.dataRowId = dataRowId;
    }
    @Override
    public Long getDataRowId() {
        return this.dataRowId;
    }

    public DataRow toDomain(){
        return DataRow.builder()
                .dataRowId(this.dataRowId)
                .build();
    }

    @Override
    public String toString() {
        return "DataRowEntity{" +
                "dataRowId=" + dataRowId +
                '}';
    }
}
