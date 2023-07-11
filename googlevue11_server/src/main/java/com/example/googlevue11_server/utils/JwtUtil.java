package com.example.googlevue11_server.utils;

import com.example.googlevue11_server.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    // JWT 토큰 생성을 위한 메서드
    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(User user) {
        // 안전한 키 생성

        // 토큰 생성
        String token = Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    // 토큰의 유효성 검사 등 다른 JWT 관련 메서드들...

    public String extractToken(String authorizationHeader) {
        // Authorization 헤더에서 토큰 추출하는 로직을 작성
        // 예: "Bearer {token}" 형식의 토큰에서 "{token}" 부분을 추출
        String[] parts = authorizationHeader.split(" ");
        if (parts.length == 2 && parts[0].equalsIgnoreCase("Bearer")) {
            return parts[1];
        } else {
            throw new IllegalArgumentException("Invalid authorization header");
        }
    }

    public boolean authenticateToken(String token) {
        try {
            // 서버 측에서 안전한 방식으로 비밀 키를 관리하고 가져옴
            SecretKey secretKey = getSecretKey(); // 비밀 키를 가져오는 메서드를 구현해야 함

            //서버에서 생성돼, 브라우저로 보낸 토큰을 다시, 헤더에 포함시켜 서버로 보내(a), 서버에서 그 유효성을 판단한다.
            // 위 a를 내 맘대로 유효성 판단하는게 아니라, 밑처럼 매소드가 이미 다 있다. 230707
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            Claims claims = claimsJws.getBody();

            // 토큰 유효성 검증 로직을 추가하여 claims와 사용자 정보(user)를 비교하여 검증합니다.
            // 예를 들어, 사용자 정보와 claims에서 추출한 정보를 비교하여 토큰의 유효성을 검증합니다.
            // ...

            return true;  // 유효한 토큰인 경우 true 반환
        } catch (Exception e) {
            return false;  // 유효하지 않은 토큰인 경우 false 반환
        }
    }

    private SecretKey getSecretKey() {
        // 비밀 키를 가져오는 로직을 구현해야 합니다.
        // 예를 들어, 환경 변수, 보안 저장소, 구성 파일 등에서 비밀 키를 안전하게 가져올 수 있습니다.

        // 비밀 키를 생성하는 방법:
       // SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // 또는 직접 비밀 키를 생성할 수도 있습니다:
        // byte[] keyBytes = "your-secret-key".getBytes(StandardCharsets.UTF_8);
        // SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

        // 가져온 비밀 키를 반환
        // return secretKey;
        return key; // 실제로는 비밀 키를 가져오는 로직을 구현하여 반환해야 합니다.
    }
}
