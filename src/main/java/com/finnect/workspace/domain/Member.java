package com.finnect.workspace.domain;

import com.finnect.workspace.domain.state.MemberState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member implements MemberState {
    private final Long userId;
    private final Long workspaceId;

    private final String nickname;
    private final String role;
    private final String phone;
}
