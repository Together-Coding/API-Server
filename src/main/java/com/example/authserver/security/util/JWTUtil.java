package com.example.authserver.security.util;

import com.example.authserver.security.dto.JwtDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
public class JWTUtil {

    /**
     * 임의의 비밀키
     */
    private final String SECRET_KEY = "INHA!@!^!^^%COMputerSciEnCE16!^HiTEcH";

    private final long EXPIRE = 60 * 24 * 30;

    public String generateToken(String content) throws Exception {

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(EXPIRE).toInstant()))
                .claim("sub", content)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes("UTF-8"))
                .compact();
    }

    public JwtDTO validateAndExtract(String tokenStr) throws Exception {
        JwtDTO jwtDTO = null;

        try {
            DefaultJws defaultJws = (DefaultJws) Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes("UTF-8")).parseClaimsJws(tokenStr);

            log.info(defaultJws.toString());
            log.info(defaultJws.getBody().getClass().toString());

            DefaultClaims claims = (DefaultClaims) defaultJws.getBody();

            log.info("--------------------------------");

            jwtDTO = JwtDTO.builder()
                    .isValid(true)
                    .email(claims.getSubject())
                    .issuedAt(claims.getIssuedAt())
                    .expiredAt(claims.getExpiration())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new JwtException(e.getMessage());

        }
        return jwtDTO;
    }
}

