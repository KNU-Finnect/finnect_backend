package com.finnect.workspace.application;

import com.finnect.workspace.domain.state.InvitationState;
import com.finnect.workspace.application.port.in.InviteMembersCommand;
import com.finnect.workspace.application.port.in.InviteMembersUsecase;
import com.finnect.workspace.domain.Invitation;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
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

    private static String username = "김아무개";
    private static String workspaceName = "네이버의 워크스페이스";
    private static String invitaionUrl = "https://localhost:8080";

    @Override
    public List<InvitationState> inviteMembers(List<InviteMembersCommand> cmds) {

        List<InvitationState> invitations = new ArrayList<>();

        // SMTP로 이메일 전송
        for (InviteMembersCommand cmd : cmds) {
            String receiver = cmd.getEmail();

            boolean result = sendEmail(receiver);
            invitations.add(new Invitation(receiver, result));
        }

        return invitations;
    }

    private boolean sendEmail(String receiver) {
        MimeMessagePreparator perparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                Context context = new Context();
                context.setVariable("username", username);
                context.setVariable("workspace_name", workspaceName);
                context.setVariable("invite_url", invitaionUrl);

                helper.setTo(receiver);
                helper.setSubject("[Finnect] 귀하를 워크스페이스에 초대합니다.");

                String htmlContent = templateEngine.process("email", context);
                helper.setText(htmlContent, true);
            }
        };

        try {
            this.javaMailSender.send(perparator);
        } catch (MailException e) {
            return false;
        }
        return true;
    }
}
