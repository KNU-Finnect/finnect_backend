package com.finnect.workspace.adaptor.out.persistence;

import com.finnect.workspace.application.port.out.CheckWorkspaceQuery;
import com.finnect.workspace.domain.state.WorkspaceState;
import com.finnect.workspace.application.port.out.CreateWorkspacePort;
import com.finnect.workspace.application.port.out.GetWorkspacesPort;
import com.finnect.workspace.application.port.out.UpdateWorkspacePort;
import com.finnect.common.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
class WorkspacePersistenceAdapter implements
        CreateWorkspacePort, UpdateWorkspacePort, GetWorkspacesPort, CheckWorkspaceQuery {
    private final WorkspaceRepository workspaceRepository;

    @Autowired
    WorkspacePersistenceAdapter(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    @Override
    public WorkspaceState createWorkspace(WorkspaceState state) {
        WorkspaceEntity workspaceEntity = WorkspaceEntity.builder()
                .workspaceName(state.getWorkspaceName())
                .build();

        WorkspaceState savedState = workspaceRepository.save(workspaceEntity);
        System.out.println("savedState = " + savedState);
        return savedState;
    }

    @Transactional
    @Override
    public WorkspaceState updateWorkspace(WorkspaceState state) {
        WorkspaceEntity workspaceEntity = workspaceRepository.findById(state.getWorkspaceId())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 워크스페이스입니다."));

        workspaceEntity.updateWorkspace(state.getWorkspaceName());

        return workspaceEntity;
    }

    @Override
    public WorkspaceState getWorkspaceById(Long workspaceId) {

        return workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 워크스페이스입니다."));
    }

    @Override
    public List<WorkspaceState> getWorkspacesByUserId(Long userId) {
        List<WorkspaceState> workspaceStates = workspaceRepository.getAllByUserId(userId);

        return workspaceStates;
    }

    @Override
    public boolean checkWorkspaceExists(Long workspaceId) {
        return workspaceRepository.existsById(workspaceId);
    }
}
