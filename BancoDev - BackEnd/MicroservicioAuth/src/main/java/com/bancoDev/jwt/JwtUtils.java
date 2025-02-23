package com.bancoDev.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtils {
    
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    public String generarToken(String username, String rol){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", "ROLE_" + rol);
        return Jwts.builder()
        .setClaims(claims)
        .setIssuer("BancoDev-Security")
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
    }

    public boolean validarToken(String token, String username){
        final String extraerUsername = extraerUsername(token);
        return extraerUsername.equals(username) && !istokenExpired(token);
    }
    
    public Claims extraerLosClaims(String token){
        return Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
    }

    public String extraerUsername(String token){
        return extraerLosClaims(token).getSubject();
    }

    public String extraerRoles(String token){
        return extraerLosClaims(token).get("roles").toString();
    }

    public boolean istokenExpired(String token){
        return extraerLosClaims(token).getExpiration().before(new Date());
    }

}
