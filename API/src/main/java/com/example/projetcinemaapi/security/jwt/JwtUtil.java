package com.example.projetcinemaapi.security.jwt;

import com.example.projetcinemaapi.domains.auth.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    public static final String ROLE_KEY = "role";
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.validity.access}")
    private long accessTokenValidity;

    @Value("${jwt.validity.refresh}")
    private long refreshTokenValidity;

    public String getUserName(final String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return body.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
    public Role getRole(final String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            final String roleName = body.get(ROLE_KEY).toString();
            return Role.getRole(roleName);
        } catch (Exception e) {
            return null;
        }
    }

    private String generateToken(Authentication authentication, Date expiration, String role) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public String generateAccessToken(Authentication authentication) {
        Date expirationDate = new Date(System.currentTimeMillis() + accessTokenValidity);
        String role = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());
        return generateToken(authentication, expirationDate, role);
    }

    public String generateRefreshToken(Authentication authentication) {
        Date expirationDate = new Date(System.currentTimeMillis() + refreshTokenValidity);
        return generateToken(authentication, expirationDate, null);
    }

    public String generateAccessToken(Integer subject, String role) {
        return Jwts.builder()
                .setSubject(subject.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public void validateToken(String token) {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
    }
}
