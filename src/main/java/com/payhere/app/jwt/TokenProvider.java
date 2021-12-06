package com.payhere.app.jwt;


import com.payhere.app.dto.UserDto;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class TokenProvider {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private final String secretKey = "akwnfkwj"; //토큰 체크시 필요한 암호키
    private final Long expiredTime = 1000 * 60 * 1L; //토큰 유효 시간(1분)


    //토큰 생성
    public String createToken(UserDto userDto){
        return Jwts.builder()
                .setSubject(userDto.getEmail())
                .setHeader(createHeader())  //
                .setClaims(createClaims(userDto))  //토큰에 포함될 정보
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))//토큰 만료일
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    //Header 부분 설정
    private Map<String, Object> createHeader(){
        Map<String, Object> header = new HashMap<>();
        header.put("typ","JWT");
        header.put("alg","HS256");
        header.put("regDate",System.currentTimeMillis());
        return header;
    }

    //Claims 정보, email 정보를 담음
    private Map<String, Object> createClaims(UserDto userDto){
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userDto.getEmail());
        return claims;
    }

    //토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        }
        catch (ExpiredJwtException e){
            logger.info("만료된 JWT 토큰입니다.");
        }
        catch (IllegalArgumentException e){
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }


}
