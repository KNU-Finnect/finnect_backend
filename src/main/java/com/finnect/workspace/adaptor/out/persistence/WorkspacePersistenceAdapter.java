package com.finnect.workspace.adaptor.out.persistence;

import com.finnect.workspace.WorkspaceState;
import com.finnect.workspace.application.port.out.CreateWorkspacePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class WorkspacePersistenceAdapter implements
        CreateWorkspacePort {
    private final WorkspaceRepository workspaceRepository;

    @Autowired
    WorkspacePersistenceAdapter(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    @Override
    public WorkspaceState createWorkspace(WorkspaceState state) {
        WorkspaceJpaEntity workspaceJpaEntity = WorkspaceJpaEntity.builder()
                .workspaceName(state.getWorkspaceName())
                .build();

        WorkspaceState savedState = workspaceRepository.save(workspaceJpaEntity);

        return savedState;
    }
}
