package com.finnect.user.application.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

class JwtProviderTests {

    private final String SECRET = "or69fQhmqn3DAmmb7oQPAIAgb3v0GN9aGjxrdlxu6FIB288g5oWzscuf33ONtnpo";

    private final String GIVEN_USERNAME = "user@gmail.com";
    private final String GIVEN_PASSWORD = "";
    private final String GIVEN_USER_ID = "1";

    private UsernamePasswordAuthenticationToken givenAuthenticationToken() {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                GIVEN_USERNAME,
                GIVEN_PASSWORD
        );
        authenticationToken.setDetails(GIVEN_USER_ID);
        return authenticationToken;
    }

    @Test
    void authentication_used_to_generate_token_should_equals_authentication_obtained_from_token() {
        // given
        JwtProvider tokenProvider = new JwtProvider(SECRET, 180L);
        Authentication authenticationToken = givenAuthenticationToken();

        // when
        String accessToken = tokenProvider.generateAccessToken(authenticationToken);
        Authentication authentication = tokenProvider.obtainAuthentication(accessToken);

        // then
        Assertions.assertEquals(authentication.getPrincipal(), GIVEN_USERNAME);
        Assertions.assertEquals(authentication.getCredentials(), GIVEN_PASSWORD);
        Assertions.assertEquals(authentication.getDetails(), GIVEN_USER_ID);
    }

    @Test
    void expired_token_should_be_invalid() {
        // given
        JwtProvider tokenProvider = new JwtProvider(SECRET, 0L);
        String accessToken = tokenProvider.generateAccessToken(givenAuthenticationToken());

        // when
        boolean valid = tokenProvider.validateToken(accessToken);

        // then
        Assertions.assertFalse(valid);
    }
}
