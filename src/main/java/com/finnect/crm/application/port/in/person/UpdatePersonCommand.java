package com.finnect.crm.application.port.in.person;

import com.finnect.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@Getter
public class UpdatePersonCommand extends SelfValidating<UpdatePersonCommand> {

    @NotNull(message = "Person ID은 null값 일 수 없습니다.")
    private final Long personId;
    private final String personName;
    private final String personRole;
    private final String personEmail;
    private final String personPhone;

    private static final Pattern STRING_PATTERN = Pattern.compile("^[a-zA-Z가-힣]+$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{3}-\\d{3,4}-\\d{4}$");

    public UpdatePersonCommand(Long personId, String personName, String personRole, String personEmail, String personPhone) {
        this.personId = personId;

        this.personName = personName;
        if (personName != null){
            isSmallerThan("이름", personName, 50);
            checkFormat(personName, STRING_PATTERN);
        }

        this.personRole = personRole;
        if (personRole != null){
            isSmallerThan("직급", personRole, 50);
            checkFormat(personRole, STRING_PATTERN);
        }

        this.personEmail = personEmail;
        if (personEmail != null) {
            isSmallerThan("이메일", personEmail, 50);
            checkFormat(personEmail, EMAIL_PATTERN);
        }

        this.personPhone = personPhone;
        if (personPhone != null) {
            isSmallerThan("연락처", personPhone, 50);
            checkFormat(personPhone, PHONE_PATTERN);
        }

        this.validateSelf();
    }

    private static void checkFormat(String target, Pattern pattern) {
        if (pattern.matcher(target).matches())
            return;
        throw new RuntimeException(target + "이 형식에 맞지 않습니다.");
    }

    private static void isSmallerThan(String fieldName, String content, int size) {
        if (content.getBytes(StandardCharsets.UTF_8).length > size)
            throw new RuntimeException(fieldName + "은/는 " + size + "byte보다 작거나 같아야 합니다.");
    }
}
