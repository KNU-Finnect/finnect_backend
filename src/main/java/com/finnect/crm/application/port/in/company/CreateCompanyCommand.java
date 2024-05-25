package com.finnect.crm.application.port.in.company;

import com.finnect.common.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@Getter
public class CreateCompanyCommand extends SelfValidating<CreateCompanyCommand> {

    @NotEmpty(message = "도메인은 공백이거나 빈 문자열일 수 없습니다.")
    private final String domain;
    @NotEmpty(message = "회사명은 공백이거나 빈 문자열일 수 없습니다.")
    private final String companyName;

    private static final String URL_PATTERN = "^[a-zA-Z가-힣.\\-_/:]+$";
    private static final Pattern pattern = Pattern.compile(URL_PATTERN);

    public CreateCompanyCommand(String domain, String companyName) {
        this.domain = domain;
        checkUrlFormat(domain);
        isSmallerThan("도메인", domain, 255);
        this.companyName = companyName;
        isSmallerThan("회사명", companyName, 65532);
    }

    private static void checkUrlFormat(String url) {
        if (pattern.matcher(url).matches())
            return;
        throw new RuntimeException("회사 도메인이 URL 형식에 맞지 않습니다.");
    }

    private static void isSmallerThan(String fieldName, String content, int size) {
        if (content.getBytes(StandardCharsets.UTF_8).length > size)
            throw new RuntimeException(fieldName + "은/는 " + size + "byte보다 작거나 같아야 합니다.");
    }
}