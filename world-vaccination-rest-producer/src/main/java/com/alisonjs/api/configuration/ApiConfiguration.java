package com.alisonjs.api.configuration;

import com.alisonjs.business.repository.DatasetRepository;
import com.alisonjs.business.repository.UserRepository;
import com.alisonjs.business.service.DatasetService;
import com.alisonjs.business.service.UserService;
import com.alisonjs.business.service.impl.DatasetServiceImpl;
import com.alisonjs.business.service.impl.UserServiceImpl;
import com.alisonjs.security.authentication.UserAuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@PropertySource(value = { "classpath:/application.properties" }, ignoreResourceNotFound = true)
@ComponentScan(basePackages = { "com.alisonjs" })
public class ApiConfiguration {

	@Bean
	UserService userService(UserRepository userRepository) {
		return new UserServiceImpl(userRepository);
	}

	@Bean
	DatasetService datasetService(DatasetRepository datasetRepository) {
		return new DatasetServiceImpl(datasetRepository);
	}

}
