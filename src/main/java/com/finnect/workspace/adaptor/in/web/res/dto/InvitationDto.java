package com.finnect.workspace.adaptor.in.web.res.dto;

import com.finnect.workspace.domain.state.InvitationState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvitationDto {
    String email;
    Boolean succeed;

    public InvitationDto(InvitationState state) {
        this.email = state.getReceiver();
        this.succeed = state.getSucceed();
    }
}