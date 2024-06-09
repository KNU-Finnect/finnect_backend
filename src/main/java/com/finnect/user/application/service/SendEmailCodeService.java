package com.finnect.user.application.service;

import com.finnect.user.application.port.in.SendEmailCodeUseCase;
import com.finnect.user.application.port.in.command.SendEmailCodeCommand;
import com.finnect.user.application.port.out.SaveEmailCodePort;
import com.finnect.user.domain.EmailCode;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class SendEmailCodeService implements SendEmailCodeUseCase {

    private final SaveEmailCodePort saveEmailCodePort;

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final Long emailExpirationSecond;

    @Autowired
    public SendEmailCodeService(
            SaveEmailCodePort saveEmailCodePort,
            JavaMailSender emailSender,
            SpringTemplateEngine templateEngine,
            @Value("${backend.email-expiration-second}") Long emailExpirationSecond
    ) {
        this.saveEmailCodePort = saveEmailCodePort;

        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        this.emailExpirationSecond = emailExpirationSecond;
    }

    @Override
    public void sendEmailCode(SendEmailCodeCommand command) {
        EmailCode emailCode = EmailCode.builder()
                .email(command.getEmail())
                .number(RandomUtils.nextInt(0, 999999))
                .expirationSecond(emailExpirationSecond)
                .build();

        MimeMessagePreparator perparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(emailCode.getEmail());
            helper.setSubject("[Finnect] 인증번호를 입력해주세요.");

            Context context = new Context();
            context.setVariable("code", emailCode.getNumber());
            helper.setText(templateEngine.process("emailCode", context), true);
        };
        emailSender.send(perparator);

        saveEmailCodePort.saveEmailCode(emailCode);
    }
}
