package com.finnect.workspace.application;

import com.finnect.user.application.port.in.CheckSignupQuery;
import com.finnect.user.application.port.in.command.CheckSignupsCommand;
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
    private final CheckSignupQuery checkSignupQuery;

    @Override
    public List<InvitationState> inviteMembers(List<InviteMembersCommand> cmds) {

        List<InvitationState> invitations = new ArrayList<>();

        String workspaceName = getWorkspaceQuery.getWorkspace(cmds.get(0).getWorkspaceId()).getWorkspaceName();
        Map<String, Boolean> signupMap = checkSignupQuery.checkSignups(
                new CheckSignupsCommand(
                        cmds.stream()
                        .map(InviteMembersCommand::getEmail)
                        .collect(Collectors.toList())
                ));

        // SMTP로 이메일 전송
        for (InviteMembersCommand cmd : cmds) {
            Invitation invitation = Invitation.of(
                    cmd.getEmail(),
                    "임의의 이름",
                    workspaceName
            );

            if (signupMap.get(cmd.getEmail()))
                invitation.sendEmail(javaMailSender, templateEngine);
            else
                log.info(invitation.getReceiver() + "는 가입되지 않은 이메일입니다.");
            invitations.add(invitation);
        }

        return invitations;
    }
}
