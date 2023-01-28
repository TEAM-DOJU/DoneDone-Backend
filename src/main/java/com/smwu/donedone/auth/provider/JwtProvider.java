package com.smwu.donedone.auth.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    public String createJwtToken(final String email) {

        final Date now = new Date(System.currentTimeMillis());
        final Date expirationTime = new Date(System.currentTimeMillis() + 60 * 60 * 24);

        final Claims claims = generateClaims(email);

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Claims generateClaims(final String email) {
        final Claims claims = Jwts.claims();
        claims.put("email", email);
        return claims;
    }
}
