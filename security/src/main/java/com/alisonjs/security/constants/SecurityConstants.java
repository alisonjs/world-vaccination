package com.alisonjs.security.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {

    public static int JWT_EXP_DAYS;

    public static String API_KEY;

    public static String JWT_PROVIDER;

    public static String JWT_ROLE_KEY;

    public static final String JWT_INVALID_MESSAGE = "Invalid JWT token";

    @Value("${world-vaccination.security.jwt-exp-days}")
    public void setJWT_EXP_DAYS(int jwtExpDays){
        JWT_EXP_DAYS = jwtExpDays;
    }

    @Value("${world-vaccination.security.api-key}")
    public void setAPI_KEY(String apiKey){
        API_KEY = apiKey;
    }

    @Value("${world-vaccination.security.jwt-provider}")
    public void setJWT_PROVIDER(String jwtProvider){
        JWT_PROVIDER = jwtProvider;
    }

    @Value("${world-vaccination.security.jwt-role-key}")
    public void setJWT_ROLE_KEY(String jwtRoleKey){
        JWT_ROLE_KEY = jwtRoleKey;
    }

}
