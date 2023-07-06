package com.example.googlevue11_server.utils;

import com.example.googlevue11_server.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    // JWT 토큰 생성을 위한 메서드
    public String generateToken(User user) {
        // 안전한 키 생성
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // 토큰 생성
        String token = Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    // 토큰의 유효성 검사 등 다른 JWT 관련 메서드들...
}
