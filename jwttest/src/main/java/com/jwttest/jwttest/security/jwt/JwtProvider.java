package com.jwttest.jwttest.security.jwt;

import com.jwttest.jwttest.security.entity.MainUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        MainUser mainUser = (MainUser) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(mainUser.getEmail())
                .setIssuedAt(new Date (new Date().getTime()))
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    public String getEmailFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;

        }catch (MalformedJwtException e ){
            logger.error("Malformed token");
        }catch (UnsupportedJwtException e ){
            logger.error("Unsupported token" + e);
        }catch (ExpiredJwtException e ){
            logger.error("Expired token");
        }catch (IllegalArgumentException e ){
            logger.error("Empty token");
        }catch (SignatureException e){
            logger.error("Signature failed");
        }
        return false;
    }

}
