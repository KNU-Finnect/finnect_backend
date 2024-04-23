package com.finnect.auth.domain;

import com.finnect.auth.WorkspaceId;
import org.springframework.security.core.GrantedAuthority;

public record WorkspaceAuthority(WorkspaceId workspaceId) implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "workspace=%s".formatted(workspaceId);
    }
}
