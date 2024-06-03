package com.finnect.user.adapter.out.jwt;

import com.finnect.user.adapter.out.jwt.entity.JwtAuthentication;
import com.finnect.user.application.port.out.GenerateAccessTokenPort;
import com.finnect.user.application.port.out.ObtainAuthenticationPort;
import com.finnect.user.state.UserAuthenticationState;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtProvider implements ObtainAuthenticationPort, GenerateAccessTokenPort {

    private final Long expirationSecond;

    private final Key key;
    private final JwtParser parser;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-expiration-second}") Long expirationSecond
    ) {
        this.expirationSecond = expirationSecond;

        key = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret)
        );
        parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = parser.parseClaimsJws(token).getBody();
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public UserAuthenticationState obtainAuthentication(String token) {
        Claims claims = parser.parseClaimsJws(token).getBody();

        return JwtAuthentication.builder()
                .userId(String.valueOf(claims.get("uid")))
                .username(claims.getSubject())
                .authorities(Collections.singleton("wid=%s".formatted(String.valueOf(claims.get("wid")))))
                .build();
    }

    public String generateAccessToken(@NonNull UserAuthenticationState authentication) {
        Date now = new Date();

        Map<String, String> claims = new HashMap<>();
        claims.put("uid", String.valueOf(authentication.getUserId()));
        for (String authority: authentication.getAuthorities()) {
            String[] s = authority.split("=");
            claims.put(s[0], s[1]);
        }

        String token = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setSubject(authentication.getUsername())
                .setIssuer("finnect")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationSecond * 1000))
                .signWith(key)
                .compact();

        log.info("Generated access token: {}", token);
        return token;
    }
}
