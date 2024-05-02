package com.finnect.user.application.jwt;

import com.finnect.user.vo.AccessToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

class JwtProviderTests {

    private final String SECRET = "or69fQhmqn3DAmmb7oQPAIAgb3v0GN9aGjxrdlxu6FIB288g5oWzscuf33ONtnpo";

    private final String GIVEN_USERNAME = "user-1";
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
        JwtProvider jwtProvider = new JwtProvider(SECRET, 180000L, 0L);
        Authentication authenticationToken = givenAuthenticationToken();

        // when
        AccessToken accessToken = jwtProvider.generateAccessToken(authenticationToken);
        Authentication authentication = jwtProvider.obtainAuthentication(accessToken.value());

        // then
        Assertions.assertEquals(authentication.getPrincipal(), GIVEN_USERNAME);
        Assertions.assertEquals(authentication.getCredentials(), GIVEN_PASSWORD);
        Assertions.assertEquals(authentication.getDetails(), GIVEN_USER_ID);
    }

    @Test
    void expired_token_should_be_invalid() {
        // given
        JwtProvider jwtProvider = new JwtProvider(SECRET, 0L, 0L);
        AccessToken accessToken = jwtProvider.generateAccessToken(givenAuthenticationToken());

        // when
        boolean valid = jwtProvider.validateToken(accessToken.value());

        // then
        Assertions.assertFalse(valid);
    }
}
