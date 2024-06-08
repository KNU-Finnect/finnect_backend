package com.finnect.user.adapter.in.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finnect.common.ApiUtils;
import com.finnect.user.adapter.in.security.response.SigninResponse;
import com.finnect.user.application.port.in.GetNameUseCase;
import com.finnect.user.application.port.in.IssueUseCase;
import com.finnect.user.application.port.in.command.IssueCommand;
import com.finnect.user.domain.UserAuthentication;
import com.finnect.user.state.TokenPairState;
import com.finnect.user.vo.UserId;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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
    private final GetNameUseCase getNameUseCase;

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
        UserAuthentication authentication = UserAuthentication.from(authResult);
        UserId userId = UserId.parseOrNull(authentication.getUserId());

        // Access Token 발행
        IssueCommand command = IssueCommand.builder()
                .authentication(authentication)
                .build();
        TokenPairState tokenPair = issueUseCase.issue(command);

        // Access Token 헤더
        response.setHeader(HttpHeaders.AUTHORIZATION, tokenPair.getAccessToken().toBearerString());

        // Refresh Token 쿠키
        ResponseCookie cookie = ResponseCookie.from("Refresh", tokenPair.getRefreshToken().toString())
                .path("/")
                .httpOnly(false) // 본래 true여야 하지만 보안 정책이 너무 까다로워서 이 프로젝트에서는 false로 사용한다.
                .secure(false) // 본래 true여야 하지만 보안 정책이 너무 까다로워서 이 프로젝트에서는 false로 사용한다.
                .sameSite("Lax")
                .maxAge(refreshExpirationSecond.intValue())
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        String responseBody = new ObjectMapper().writeValueAsString(
                ApiUtils.success(
                        HttpStatus.OK,
                        SigninResponse.builder()
                                .refreshToken(tokenPair.getRefreshToken().toString())
                                .personalName(getNameUseCase.getNameById(userId))
                                .build()
                )
        );

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseBody);
    }
}
