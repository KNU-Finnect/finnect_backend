package com.finnect.workspace.adaptor.out.persistence;


import com.finnect.workspace.domain.state.WorkspaceState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workspace")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
class WorkspaceEntity implements WorkspaceState {

    @Id
    @GeneratedValue()
    private Long workspaceId;

    @Column
    private String workspaceName;

    void updateWorkspace(String newName) {
        this.workspaceName = newName;
    }
}
