package com.alisonjs.security.authentication;

import com.alisonjs.business.domain.User;
import com.alisonjs.security.provider.UserToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAuthenticationManager {

    private final AuthenticationManager authenticationManager;

    private final JwtManager jwtManager;

    public UserAuthenticationManager(AuthenticationManager authenticationManager, JwtManager jwtManager) {
        this.authenticationManager = authenticationManager;
        this.jwtManager = jwtManager;
    }

    public UserToken auth(User user){

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        org.springframework.security.core.userdetails.User userSpring
                = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        List<String> roles = userSpring.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return jwtManager.createToken(userSpring.getUsername(), roles);
    }

}
