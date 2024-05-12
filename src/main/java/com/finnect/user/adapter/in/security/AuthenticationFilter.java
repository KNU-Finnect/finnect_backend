package com.finnect.user.adapter.in.security;

import com.finnect.user.application.port.in.command.IssueCommand;
import com.finnect.user.application.service.TokenService;
import com.finnect.user.state.TokenPairState;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final TokenService tokenService;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        // Extract from request parameters
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, token);
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {
        logger.info("Successful authentication: %s".formatted(authResult));

        IssueCommand command = IssueCommand.builder()
                .authentication(authResult)
                .build();

        TokenPairState tokenPair = tokenService.issue(command);

        response.setHeader(
                HttpHeaders.AUTHORIZATION,
                tokenPair.getAccessToken().toBearerString()
        );

        Cookie cookie = new Cookie("Refresh", tokenPair.getRefreshToken().toString());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
