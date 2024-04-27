package com.finnect.cell.adapter.out.persistence;

import com.finnect.cell.domain.state.ColumnState;
import com.finnect.mockDomain.WorkspaceEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

@Entity(name = "data_column")
public class DataColumnEntity implements ColumnState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long columnId;

    private Long workspaceId;

    protected DataColumnEntity() {
    }
    @Builder
    public DataColumnEntity(Long columnId, Long workspaceId) {
        this.columnId = columnId;
        this.workspaceId = workspaceId;
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
    public String toString() {
        return "DataColumnEntity{" +
                "columnId=" + columnId +
                ", workspaceId=" + workspaceId +
                '}';
    }
}
