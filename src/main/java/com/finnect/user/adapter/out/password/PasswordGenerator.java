package com.finnect.user.adapter.out.password;

import com.finnect.user.application.port.out.GeneratePasswordPort;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class PasswordGenerator implements GeneratePasswordPort {

    public String generateRandomPassword() {
        StringBuilder builder = new StringBuilder();

        // 영어 대문자, 영어 소문자, 특수문자를 각각 하나씩 먼저 넣어준다.
        builder.append(RandomStringUtils.randomAlphabetic(1).toUpperCase());
        builder.append(RandomStringUtils.randomAlphabetic(1).toLowerCase());
        builder.append(RandomStringUtils.random(1, "-!*_"));

        // 나머지는 랜덤으로 채운다.
        builder.append(RandomStringUtils.randomAlphabetic(7, 12));

        // 문자열을 리스트로 변환하여 셔플한다.
        ArrayList<Character> chars = new ArrayList<>(builder.toString().chars()
                .mapToObj(e -> (char)e)
                .toList());
        Collections.shuffle(chars);

        // 셔플된 리스트를 문자열로 합쳐서 반환한다.
        return StringUtils.join(chars.toArray());
    }
}
