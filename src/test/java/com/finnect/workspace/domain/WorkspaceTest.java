package com.finnect.workspace.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkspaceTest {

    @Test
    @DisplayName("워크스페이스를 ID와 이름으로 생성")
    void createWorkspaceTest() {
        Long givenId = 100L;
        String givenName = "test workspace";

        Workspace workspace = new Workspace(givenId, givenName);

        assertEquals(workspace.getWorkspaceId(), givenId);
        assertEquals(workspace.getWorkspaceName(), givenName);
    }

    @Test
    @DisplayName("워크스페이스를 이름으로 생성")
    void createWorkspaceWithourIdTest() {
        String givenName = "test workspace";

        Workspace workspace = new Workspace(givenName);

        assertEquals(workspace.getWorkspaceName(), givenName);
    }

}