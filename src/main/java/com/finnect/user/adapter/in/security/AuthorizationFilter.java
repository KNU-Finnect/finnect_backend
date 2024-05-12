package com.finnect.user.adapter.in.security;

import com.finnect.user.application.port.in.command.AuthorizeCommand;
import com.finnect.user.application.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            logger.info("Authenticating access token: %s".formatted(header));

            AuthorizeCommand command = AuthorizeCommand.builder()
                    .bearerToken(header)
                    .build();

            tokenService.authorize(command);
        } else {
            logger.info("No access tokens");
        }

        filterChain.doFilter(request, response);
    }
}
