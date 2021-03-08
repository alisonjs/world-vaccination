package com.alisonjs.security.configuration;

import com.alisonjs.security.provider.UserDetailsProvider;
import com.alisonjs.security.utils.CustomPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@PropertySource(value = { "classpath:/security-application.properties", "classpath:/application.properties" },
        ignoreResourceNotFound = true)
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

    @Bean(name= BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.GET, "/dataset/**")
                .antMatchers(HttpMethod.POST, "/users/login");
    }
}
