package com.finnect.workspace.application.port.in;

import com.finnect.common.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@Getter
public class InviteMembersCommand extends SelfValidating<InviteMembersCommand> {
    @NotEmpty(message = "e-mail은 공백이거나 빈 문자열일 수 없습니다.")
    private final String email;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public InviteMembersCommand(String email) {
        this.email = email;
        isSmallerThan(this.email, 50);
        this.validateSelf();
    }

    private void isSmallerThan(String name, int size) {
        if (name.getBytes(StandardCharsets.UTF_8).length > size)
            throw new RuntimeException("e-mail은 " + size + "byte보다 작거나 같아야 합니다.");
    }

    private void checkEmailFormat(String email) {
        if (pattern.matcher(email).matches())
            return;
        throw new RuntimeException("e-mail의 형식이 올바르지 않습니다.");
    }
}
