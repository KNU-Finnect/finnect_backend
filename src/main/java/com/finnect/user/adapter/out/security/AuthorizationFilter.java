package com.finnect.user.adapter.out.security;

import com.finnect.user.adapter.out.jwt.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null && header.startsWith("Bearer")) {
            // Resolve token
            String accessToken = header.substring(7);
            logger.info("Validating access token: %s".formatted(accessToken));

            if (jwtProvider.validateToken(accessToken)) {
                // Allow authentication object to be viewed from anywhere
                Authentication authentication = jwtProvider.obtainAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("Authenticated access token: %s".formatted(accessToken));
            } else {
                logger.info("Invalid access token: %s".formatted(accessToken));
            }
        } else {
            logger.info("No access tokens");
        }

        filterChain.doFilter(request, response);
    }
}
