package com.finnect.workspace.application;

import com.finnect.user.application.port.in.CheckSignupQuery;
import com.finnect.user.application.port.in.GetPersonalNameQuery;
import com.finnect.user.application.port.in.command.CheckSignupsCommand;
import com.finnect.user.application.port.out.LoadUserPort;
import com.finnect.user.vo.UserId;
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
    private final CheckSignupQuery checkSignupQuery;
    private final SearchMemberPort searchMemberPort;
    private final LoadUserPort loadUserPort;

    @Override
    public List<InvitationState> inviteMembers(List<InviteMembersCommand> cmds) {

        List<InvitationState> invitations = new ArrayList<>();

        WorkspaceState workspace = getWorkspaceQuery.getWorkspace(cmds.get(0).getWorkspaceId());
        String senderName = getPersonalNameQuery.getPersonalName(UserId.parseOrNull(cmds.get(0).getUserId()));

        // 초대할 사람들의 이메일 목록을 주고, 각자 가입되어 있는지 확인
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
                    senderName,
                    workspace.getWorkspaceId(),
                    workspace.getWorkspaceName()
            );

            // 가입하지 않은 사용자
            if (!signupMap.get(cmd.getEmail())) {
                invitation.updateResult(InvitationResult.YET_SIGNUP);
                invitations.add(invitation);
                continue;
            }

            // 가입한 사용자라면, Member가 존재하는지 확인
            if (searchMemberPort.searchMember(loadUserPort.loadUserByEmail(cmd.getEmail()).getId().value(), cmd.getWorkspaceId())) {
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
