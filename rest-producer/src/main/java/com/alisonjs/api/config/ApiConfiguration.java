package com.alisonjs.api.config;

import com.alisonjs.api.dto.UserDto;
import com.alisonjs.api.dto.mapper.UserDtoMapper;
import com.alisonjs.business.repository.UserRepository;
import com.alisonjs.business.service.UserService;
import com.alisonjs.business.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "classpath:/application.properties" }, ignoreResourceNotFound = true)
@ComponentScan(basePackages = { "com.alisonjs.persistence" })
public class ApiConfiguration {

    @Bean
    UserService userService(UserRepository userRepository){
        return new UserServiceImpl(userRepository);
    }
}
