package org.zerock.j10.user.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import lombok.extern.log4j.Log4j2;


import java.time.ZonedDateTime;
import java.util.Date;


@Log4j2
public class JWTUtil {

    private String secretKey = "zerock12345678";

    //1month
    private long expire = 60 * 24* 30;

    /**
     *  토큰을 생성하는 함수
     * @param content -> email
     * @return JWT토큰객체
     * @throws Exception
     */
    public String generateToken(String content) throws Exception{

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()))
                //.setExpiration(Date.from(ZonedDateTime.now().plusSeconds(1).toInstant()))
                .claim("sub", content)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8"))
                .compact();
    }

    /**
     * 토큰을 검증하는 함수
     * @param tokenStr -> 토큰값
     * @return email값
     * @throws Exception
     */
    public String validateAndExtract(String tokenStr)throws Exception {

        String contentValue = null;

        try {
            DefaultJws defaultJws = (DefaultJws) Jwts.parser()
                    .setSigningKey(secretKey.getBytes("UTF-8")).parseClaimsJws(tokenStr);

            log.info(defaultJws);

            log.info(defaultJws.getBody().getClass());

            DefaultClaims claims = (DefaultClaims) defaultJws.getBody();

            log.info("------------------------");

            contentValue = claims.getSubject();

        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            contentValue = null;
            throw e;
        }
        return contentValue;
    }

}