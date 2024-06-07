package com.finnect.workspace.application.port.in;

import com.finnect.common.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@Getter
public class InviteMembersCommand extends SelfValidating<InviteMembersCommand> {
    @NotEmpty(message = "e-mail은 공백이거나 빈 문자열일 수 없습니다.") @Email
    private final String email;
    private final String senderName;
    private final String workspaceName;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public InviteMembersCommand(String email, String senderName, String workspaceName) {
        this.email = email;
        isSmallerThan("email", this.email, 50);
        this.senderName = senderName;
        isSmallerThan("senderName", this.senderName, 50);
        this.workspaceName = workspaceName;
        isSmallerThan("workspaceName", this.workspaceName, 50);
        this.validateSelf();
    }

    private void isSmallerThan(String fieldName, String value, int size) {
        if (value.getBytes(StandardCharsets.UTF_8).length > size)
            throw new RuntimeException(fieldName + "은/는 " + size + "byte보다 작거나 같아야 합니다.");
    }
}
