package com.finnect.workspace.application;

import com.finnect.user.application.port.in.CheckEmailQuery;
import com.finnect.user.application.port.in.GetPersonalNameQuery;
import com.finnect.common.vo.UserId;
import com.finnect.workspace.application.port.in.GetWorkspaceQuery;
import com.finnect.workspace.application.port.out.SearchMemberPort;
import com.finnect.workspace.domain.InvitationResult;
import com.finnect.workspace.domain.state.InvitationState;
import com.finnect.workspace.application.port.in.InviteMembersCommand;
import com.finnect.workspace.application.port.in.InviteMembersUsecase;
import com.finnect.workspace.domain.Invitation;
import com.finnect.workspace.domain.state.WorkspaceState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InviteMembersService implements InviteMembersUsecase {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private final GetWorkspaceQuery getWorkspaceQuery;
    private final GetPersonalNameQuery getPersonalNameQuery;
    private final CheckEmailQuery checkEmailQuery;
    private final SearchMemberPort searchMemberPort;

    @Override
    public List<InvitationState> inviteMembers(List<InviteMembersCommand> cmds) {

        List<InvitationState> invitations = new ArrayList<>();

        WorkspaceState workspace = getWorkspaceQuery.getWorkspace(cmds.get(0).getWorkspaceId());
        String senderName = getPersonalNameQuery.getPersonalName(UserId.parseOrNull(cmds.get(0).getUserId()));

        Map<String, Boolean> signupMap = checkEmailQuery.checkEmailsExist(
                cmds.stream()
                        .map(InviteMembersCommand::getEmail)
                        .collect(Collectors.toList())
        );

        // SMTP로 이메일 전송
        for (InviteMembersCommand cmd : cmds) {
            Invitation invitation = Invitation.of(
                    cmd.getEmail(),
                    senderName,
                    workspace.getWorkspaceId(),
                    workspace.getWorkspaceName()
            );

            if (!signupMap.get(cmd.getEmail())) {
                invitation.updateResult(InvitationResult.YET_SIGNUP);
                invitations.add(invitation);
                continue;
            }
            if (searchMemberPort.searchMember(cmd.getUserId(), cmd.getWorkspaceId())) {
                invitation.updateResult(InvitationResult.ALREADY_MEMBER);
                invitations.add(invitation);
                continue;
            }

            invitation.sendEmail(javaMailSender, templateEngine);
            invitations.add(invitation);
        }

        return invitations;
    }
}
