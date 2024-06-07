package com.finnect.workspace.application;

import com.finnect.workspace.domain.state.WorkspaceState;
import com.finnect.workspace.application.port.in.GetWorkspaceQuery;
import com.finnect.workspace.application.port.out.GetWorkspacesPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class GetWorkspaceService implements GetWorkspaceQuery {

    private final GetWorkspacesPort getWorkspacesPort;

    @Override
    public List<WorkspaceState> getWorkspaces(Long userId) {
        return getWorkspacesPort.getWorkspacesByUserId(userId);
    }
}
