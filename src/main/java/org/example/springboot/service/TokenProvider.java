package org.example.springboot.service;

import org.example.springboot.domain.JwtProperties;
import org.example.springboot.domain.User;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

// Service Annotation 지정
@Service
// RequiredArgsConstructor Annotation 으로 의존성 주입을 위한 생성자 자동 생성
@RequiredArgsConstructor
public class TokenProvider {
    // 의존성 주입 필요(위에서 해줌) 리포지토리 객체 생성
    private final JwtProperties jwtProperties;

    // JWT 토큰 생성. 유저와 만료 기간 Arguments
    public String generateToken(User user, Duration expiredAt) {
        // 현재 시각 날짜 객체 생성
        Date now = new Date();
        // user 와 현재 시각에 만료 시간을 더한 값으로 토큰 생성
        return makeToken(user, new Date(now.getTime() + expiredAt.toMillis()));
    }

    // 위에서 사용한 makeToken 메서드
    private String makeToken(User user, Date expiry) {
        // 현재 시각 날짜 객체 생성
        Date now = new Date();

        //
        return Jwts.builder()
                // 헤더 정보 설정
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                // 발급자 설정(JWT Properties 이용)
                .setIssuer(jwtProperties.getIssuer())
                // 발급 일자는 현재 시각으로 설정
                .setIssuedAt(now)
                // 만료 일자 설정(Parameter 로 받은 값)
                .setExpiration(expiry)
                // 사용자 id 설정(여기서는 email)
                .setSubject(user.getEmail())
                // 사용자의 식별자 설정
                .claim("id", user.getId())
                // HS256 알고리즘을 사용하고, JWT Properties 에서 받은 키로 서명
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                // 문자열 형태로 반환.
                .compact();
    }
}