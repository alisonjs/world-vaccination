package com.alisonjs.security.configuration;

import com.alisonjs.security.authentication.AuthorizationFilter;
import com.alisonjs.security.provider.UserDetailsProvider;
import com.alisonjs.security.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@PropertySource(value = { "classpath:/security-application.properties", "classpath:/application.properties" },
		ignoreResourceNotFound = true)
@ComponentScan("com.alisonjs.security")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${world.vaccination.security.origin-allowed}")
	private String originAllowed;

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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable().authorizeRequests().anyRequest().authenticated();
		http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.GET, "/dataset/**")
				.antMatchers(HttpMethod.POST, "/auth/signin")
				.antMatchers("/v2/api-docs",
						"/configuration/ui",
						"/swagger-resources/**",
						"/configuration/security",
						"/swagger-ui.html",
						"/webjars/**");
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/auth/signin").allowedOrigins(originAllowed);
				registry.addMapping("/dataset/**").allowedOrigins(originAllowed);
				registry.addMapping("/users/**").allowedOrigins(originAllowed);
				registry.addMapping("/files/**").allowedOrigins(originAllowed);
			}
		};
	}
}
