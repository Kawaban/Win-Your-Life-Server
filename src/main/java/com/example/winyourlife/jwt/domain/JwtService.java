package com.example.winyourlife.jwt.domain;

import com.example.winyourlife.jwt.dto.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
record JwtService(JwtProperties properties) implements com.example.winyourlife.jwt.JwtService {
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public boolean isTokenValid(String token, JwtUser user) {
        final String username = extractUsername(token);
        return (username.equals(user.subject()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public String generateToken(JwtUser user) {
        return generateToken(new HashMap<>(), user);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, JwtUser user) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user == null ? null : user.subject())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpirationTimeMillis()))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(properties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
