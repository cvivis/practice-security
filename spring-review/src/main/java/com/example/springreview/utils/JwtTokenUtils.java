package com.example.springreview.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtils {

    public static Claims extractClaims(String token, String secretKey){ // jwt 확인
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public static boolean isExpired(String token, String secretKey){
        Date expiredDate = extractClaims(token,secretKey).getExpiration();
        return expiredDate.before(new Date());
    }
    public static String generateToken(String userName, String key, long expiredTimeMs) {
        Claims claims = Jwts.claims();
        claims.put("userName", userName); // Token에 담는 정보를 Claim이라고 함
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 발생시간
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs)) // 기간
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
