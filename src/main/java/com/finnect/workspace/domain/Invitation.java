package com.finnect.workspace.domain;

import com.finnect.workspace.InvitationState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Invitation implements InvitationState {
    private String email;
    private Boolean succeed;
}
