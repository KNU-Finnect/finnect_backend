package com.finnect.user.adapter.in.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finnect.common.ApiUtils;
import com.finnect.user.application.port.in.IssueUseCase;
import com.finnect.user.application.port.in.command.IssueCommand;
import com.finnect.user.state.TokenPairState;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final IssueUseCase issueUseCase;

    private final Long refreshExpirationSecond;

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

        TokenPairState tokenPair = issueUseCase.issue(command);

        // Access Token
        response.setHeader(HttpHeaders.AUTHORIZATION, tokenPair.getAccessToken().toBearerString());

        // Refresh Token
        ResponseCookie refreshCookie = ResponseCookie.from("Refresh", tokenPair.getRefreshToken().toString())
                .path("/")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .maxAge(refreshExpirationSecond.intValue())
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        // API Response
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(ApiUtils.success(HttpStatus.OK, null));

        // Request Body
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
