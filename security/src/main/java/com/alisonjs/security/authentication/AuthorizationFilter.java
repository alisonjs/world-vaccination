package com.alisonjs.security.authentication;

import com.alisonjs.security.constants.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtManager jwtManager;

    public AuthorizationFilter(JwtManager jwtManager) {
        this.jwtManager = jwtManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);


        try{
            if(jwt != null && jwt.startsWith(SecurityConstants.JWT_PROVIDER)){
                jwt = jwt.replace(SecurityConstants.JWT_PROVIDER, "");
                Claims claims = jwtManager.parseToken(jwt);
                String username = claims.getAudience();
                List<String> roles = (List<String>) claims.get(SecurityConstants.JWT_ROLE_KEY);

                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

                roles.forEach((role) -> grantedAuthorities.add(new SimpleGrantedAuthority(role)));

                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);

                filterChain.doFilter(request, response);

            }else{
                PrintWriter writer = response.getWriter();;

                JwtError jwtError = JwtError.builder()
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .msg(SecurityConstants.JWT_INVALID_MESSAGE)
                        .date(new Date())
                        .build();

                ObjectMapper mapper = new ObjectMapper();
                writer.write(mapper.writeValueAsString(jwtError));

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        }catch (Exception e){
            PrintWriter writer = response.getWriter();

            JwtError jwtError = JwtError.builder()
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .msg(e.getMessage())
                    .date(new Date())
                    .build();

            ObjectMapper mapper = new ObjectMapper();
            writer.write(mapper.writeValueAsString(jwtError));

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

    }
}
