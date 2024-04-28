package com.finnect.workspace.application;

import com.finnect.workspace.WorkspaceState;
import com.finnect.workspace.application.port.in.FindWorkspacesQuery;
import com.finnect.workspace.application.port.out.FindWorkspacesPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindWorkspacesService implements FindWorkspacesQuery {
    private final FindWorkspacesPort findWorkspacesPort;

    @Override
    public List<WorkspaceState> findWorkspaces(Long userId) {
        return findWorkspacesPort.findWorkspacesByUser(userId);
    }
}
