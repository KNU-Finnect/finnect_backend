package com.finnect.workspace.adaptor.out.persistence;

import com.finnect.workspace.WorkspaceState;
import com.finnect.workspace.application.port.out.CreateWorkspacePort;
import com.finnect.workspace.application.port.out.DeleteWorkspacePort;
import com.finnect.workspace.application.port.out.GetWorkspacesPort;
import com.finnect.workspace.application.port.out.UpdateWorkspacePort;
import com.finnect.workspace.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
class WorkspacePersistenceAdapter implements
        CreateWorkspacePort, UpdateWorkspacePort, GetWorkspacesPort, DeleteWorkspacePort {
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

    @Transactional
    @Override
    public WorkspaceState updateWorkspace(WorkspaceState state) {
        WorkspaceJpaEntity workspaceJpaEntity = workspaceRepository.findById(state.getWorkspaceId())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 워크스페이스입니다."));

        workspaceJpaEntity.updateWorkspace(state.getWorkspaceName());

        return workspaceJpaEntity;
    }

    @Override
    public List<WorkspaceState> getWorkspacesByUserId(Long userId) {
        List<WorkspaceState> workspaceStates = workspaceRepository.getAllByUserId(userId);

        return workspaceStates;
    }

    @Override
    public boolean delete(Long workspaceId) {
        workspaceRepository.deleteById(workspaceId);
        return true;
    }
}
