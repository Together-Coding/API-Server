package com.example.authserver.security.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
    private final String SECRET_KEY = "q1!w2@e3#r4$t5%";

    private final long EXPIRE = 60 * 24 * 30;

    public String generateToken(String content) throws Exception {

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(EXPIRE).toInstant()))
                .claim("sub", content)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes("UTF-8"))
                .compact();
    }

    public String validateAndExtract(String tokenStr) throws Exception {

        String contentValue = null;

        try {
            DefaultJws defaultJws = (DefaultJws) Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes("UTF-8")).parseClaimsJws(tokenStr);

            log.info(defaultJws.toString());
            log.info(defaultJws.getBody().getClass().toString());

            DefaultClaims claims = (DefaultClaims) defaultJws.getBody();

            log.info("--------------------------------");

            contentValue = claims.getSubject();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            contentValue = null;
        }
        return contentValue;
    }
}

