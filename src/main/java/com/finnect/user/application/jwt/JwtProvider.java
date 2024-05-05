package com.finnect.user.application.jwt;

import com.finnect.user.vo.AccessToken;
import com.finnect.user.vo.JwtPair;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Slf4j
@Component
public class JwtProvider {

    private final Long accessExpiredTime;
    private final Long refreshExpiredTime;

    private final Key key;
    private final JwtParser parser;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-expired-time}") Long accessExpiredTime,
            @Value("${jwt.refresh-expired-time}") Long refreshExpiredTime
    ) {
        this.accessExpiredTime = accessExpiredTime;
        this.refreshExpiredTime = refreshExpiredTime;

        key = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret)
        );
        parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
    }

    public Boolean validateToken(String token) {
        try {
            Claims claims = parser.parseClaimsJws(token).getBody();
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication obtainAuthentication(String token) {
        Claims claims = parser.parseClaimsJws(token).getBody();
        String userId = String.valueOf(claims.get("uid"));
        String username = claims.getSubject();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                ""
        );
        authenticationToken.setDetails(userId);
        return authenticationToken;
    }

    public JwtPair generateTokenPair(Authentication authentication) {
        Date now = new Date();
        return new JwtPair(
                generateAccessToken(authentication, now),
                generateRefreshToken(authentication, now)
        );
    }

    public AccessToken generateAccessToken(Authentication authentication) {
        return generateAccessToken(authentication, new Date());
    }

    public String generateRefreshToken(Authentication authentication) {
        return generateRefreshToken(authentication, new Date());
    }

    private AccessToken generateAccessToken(Authentication authentication, Date now) {
        String token = generateToken(authentication, now, accessExpiredTime);
        log.info("Generated access token: {}", token);
        return new AccessToken(token);
    }

    private String generateRefreshToken(Authentication authentication, Date now) {
        String token = generateToken(authentication, now, refreshExpiredTime);
        log.info("Generated refresh token: {}", token);
        return token;
    }

    private String generateToken(@NonNull Authentication authentication, Date now, Long expiredTime) {
        List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        Map<String, String> claims = new HashMap<>();
        claims.put("uid", String.valueOf(authentication.getDetails()));
        for (String authority: authorities) {
            String[] s = authority.split("=");
            claims.put(s[0], s[1]);
        }

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setSubject(authentication.getName())
                .setIssuer("finnect")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiredTime))
                .signWith(key)
                .compact();
    }
}
