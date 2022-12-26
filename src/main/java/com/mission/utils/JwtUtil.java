package com.mission.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    public static String createToken(String userName, String secretKey, long exprieTimeMs) {
        Claims claims = Jwts.claims(); // 일종의 map 이곳에 정보를 넣으면 됨
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + exprieTimeMs)) // 시간 받기
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
                ;
    }
}
