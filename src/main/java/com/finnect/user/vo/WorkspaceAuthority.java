package com.finnect.user.vo;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

public record WorkspaceAuthority(WorkspaceId workspaceId) implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "wid=%s".formatted(workspaceId);
    }

    public static WorkspaceAuthority from(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().findFirst().map(WorkspaceAuthority::from).orElse(null);
    }

    public static WorkspaceAuthority from(GrantedAuthority authority) {
        return WorkspaceAuthority.of(authority.getAuthority());
    }

    public static WorkspaceAuthority of(Collection<String> authorities) {
        return authorities.stream().findFirst().map(WorkspaceAuthority::of).orElse(null);
    }

    public static WorkspaceAuthority of(String authority) {
        String[] s = authority.split("=");

        if (Arrays.stream(s).count() == 2 && s[0].equals("wid")) {
            return new WorkspaceAuthority(WorkspaceId.parseOrNull(s[1]));
        } else {
            return null;
        }
    }
}
