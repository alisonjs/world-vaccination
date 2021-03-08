package com.alisonjs.security.configuration;

import com.alisonjs.security.provider.UserDetailsProvider;
import com.alisonjs.security.utils.CustomPasswordEncoder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.alisonjs.security")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsProvider userDetailsService;

    private final CustomPasswordEncoder passwordEncoder;

    public SecurityConfiguration(UserDetailsProvider userDetailsService, CustomPasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
