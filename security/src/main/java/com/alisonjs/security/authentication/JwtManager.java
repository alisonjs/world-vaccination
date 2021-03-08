package com.alisonjs.security.authentication;

import com.alisonjs.security.constants.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.List;

public class JwtManager {

    public static String createToken(String username, List<String> roles){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, SecurityConstants.JWT_EXP_DAYS);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(calendar.getTime())
                .claim(SecurityConstants.JWT_ROLE_KEY, roles)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.API_KEY.getBytes())
                .compact();
    }
}
