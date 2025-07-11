package com.insta_clone.api.common.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import java.util.Collections;
import java.util.Date;

/**
 * @author rua
 */

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token-validity}")
    private long validityInMilliseconds;

    /**
     * 1. 토큰 생성 (로그인 성공 시)
     */
    public String createToken(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username); // JWT "sub" 필드
        claims.put("role", role); // 커스텀 클레임

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds); // 만료시간

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)           // 발급 시각
                .setExpiration(validity)    // 만료 시각
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()) // 서명
                .compact();
    }

    /**
     * 2. 토큰에서 Authentication 객체 생성
     *    - Spring Security가 인증된 사용자 정보로 쓰는 객체
     */
    public Authentication getAuthentication(String token) {
        String username  = getUsername(token);
        // (권한 정보가 필요하면 더 넣어도 됨)
        return new UsernamePasswordAuthenticationToken(username, "", Collections.emptyList());
    }

    /**
     * 3. 토큰에서 username(subject) 추출
     */
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * 4. 토큰 유효성 검사 (서명, 만료 등)
     */
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token).getBody();
            return !claims.getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            // 위조, 만료, 파싱 에러 등 모두 false
            return false;
        }
    }

    /**
     * 5. 요청 객체에서 토큰 추출
     *    - HTTP Authorization 헤더의 Bearer ... 에서 토큰만 잘라냄
     */
    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
