package com.finnect.workspace.application.port.in;

import com.finnect.workspace.InvitationState;

import java.util.List;

public interface InviteMembersUsecase {
    List<InvitationState> inviteMembers(List<InviteMembersCommand> cmds);
}
