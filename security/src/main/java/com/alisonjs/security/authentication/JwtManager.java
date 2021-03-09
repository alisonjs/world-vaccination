package com.alisonjs.security.authentication;

import com.alisonjs.security.constants.SecurityConstants;
import com.alisonjs.security.provider.UserToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class JwtManager {

    public UserToken createToken(String username, List<String> roles){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, SecurityConstants.JWT_EXP_DAYS);
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(calendar.getTime())
                .claim(SecurityConstants.JWT_ROLE_KEY, roles)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.API_KEY.getBytes())
                .compact();

        return UserToken.builder()
                .token(jwt)
                .tokenProvider(SecurityConstants.JWT_PROVIDER)
                .expireIn(calendar.getTimeInMillis())
                .build();
    }

    public Claims parseToken(String jwt) throws JwtException {

        return Jwts.parser()
                .setSigningKey(SecurityConstants.API_KEY)
                .parseClaimsJwt(jwt)
                .getBody();
    }
}
