package com.alisonjs.security.constants;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstants {

    @Value("${world-vaccination.security.jwt-exp-days}")
    private int jwtExpDays;
    @Value("${world-vaccination.security.api-key}")
    private String apiKey;
    @Value("${world-vaccination.security.jwt-provider}")
    private String jwtProvider;
    @Value("${world-vaccination.security.role-key}")
    private String jwtRoleKey;

    public static int JWT_EXP_DAYS;

    public static String API_KEY;

    public static String JWT_PROVIDER;

    public static String JWT_ROLE_KEY;

    @Value("${world-vaccination.security.jwt-exp-days}")
    public void setJwtExpDays(int jwtExpDays){
        JWT_EXP_DAYS = jwtExpDays;
    }

    @Value("${world-vaccination.security.api-key}")
    public void setApiKey(String apiKey){
        API_KEY = apiKey;
    }

    @Value("${world-vaccination.security.jwt-provider}")
    public void setJwtProvider(String jwtProvider){
        JWT_PROVIDER = jwtProvider;
    }

    @Value("${world-vaccination.security.jwt-role-key}")
    public void setJwtRoleKey(String jwtRoleKey){
        JWT_ROLE_KEY = jwtRoleKey;
    }

}
