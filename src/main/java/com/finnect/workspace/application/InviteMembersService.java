package com.finnect.workspace.application;

import com.finnect.workspace.application.port.in.GetWorkspaceQuery;
import com.finnect.workspace.domain.state.InvitationState;
import com.finnect.workspace.application.port.in.InviteMembersCommand;
import com.finnect.workspace.application.port.in.InviteMembersUsecase;
import com.finnect.workspace.domain.Invitation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InviteMembersService implements InviteMembersUsecase {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private final GetWorkspaceQuery getWorkspaceQuery;
    @Override
    public List<InvitationState> inviteMembers(List<InviteMembersCommand> cmds) {

        List<InvitationState> invitations = new ArrayList<>();

        // SMTP로 이메일 전송
        for (InviteMembersCommand cmd : cmds) {
            Invitation invitation = Invitation.of(
                    cmd.getEmail(),
                    "임의의 이름",
                    getWorkspaceQuery.getWorkspace(cmd.getWorkspaceId()).getWorkspaceName()
            );

            invitation.sendEmail(javaMailSender, templateEngine);
            invitations.add(invitation);
        }

        return invitations;
    }
}
