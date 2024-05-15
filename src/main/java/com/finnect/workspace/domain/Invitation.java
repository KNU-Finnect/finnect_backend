package com.finnect.workspace.domain;

import com.finnect.workspace.domain.state.InvitationState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Invitation implements InvitationState {
    private String email;
    private Boolean succeed;
}
