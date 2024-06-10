package com.finnect.user.adapter.in.jwt;

import com.finnect.user.adapter.out.jwt.JwtProvider;
import com.finnect.user.adapter.out.jwt.entity.JwtAuthentication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JwtProviderTests {

    private final String SECRET = "or69fQhmqn3DAmmb7oQPAIAgb3v0GN9aGjxrdlxu6FIB288g5oWzscuf33ONtnpo";

    private final String GIVEN_USER_ID = "1";
    private final String GIVEN_USERNAME = "user@gmail.com";

    private JwtAuthentication givenAuthentication() {
        return JwtAuthentication.builder()
                .userId(GIVEN_USER_ID)
                .username(GIVEN_USERNAME)
                .build();
    }

    @Test
    void authentication_used_to_generate_token_should_equals_authentication_obtained_from_token() {
        // given
        JwtProvider tokenProvider = new JwtProvider(SECRET, 180L);
        JwtAuthentication givenAuthentication = givenAuthentication();

        // when
        String accessToken = tokenProvider.generateAccessToken(givenAuthentication);
        JwtAuthentication obtainedAuthentication = JwtAuthentication.from(tokenProvider.obtainAuthentication(accessToken));

        // then
        Assertions.assertEquals(obtainedAuthentication.getUserId(), GIVEN_USER_ID);
        Assertions.assertEquals(obtainedAuthentication.getUsername(), GIVEN_USERNAME);
    }

    @Test
    void expired_token_should_be_invalid() {
        // given
        JwtProvider tokenProvider = new JwtProvider(SECRET, 0L);
        String accessToken = tokenProvider.generateAccessToken(givenAuthentication());

        // when
        boolean valid = tokenProvider.validateToken(accessToken);

        // then
        Assertions.assertFalse(valid);
    }
}
