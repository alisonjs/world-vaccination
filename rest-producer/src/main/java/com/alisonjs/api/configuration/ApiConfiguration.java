package com.alisonjs.api.configuration;

import com.alisonjs.business.repository.DatasetRepository;
import com.alisonjs.business.repository.UserRepository;
import com.alisonjs.business.service.DatasetService;
import com.alisonjs.business.service.UserService;
import com.alisonjs.business.service.impl.DatasetServiceImpl;
import com.alisonjs.business.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "classpath:/application.properties" }, ignoreResourceNotFound = true)
@ComponentScan(basePackages = { "com.alisonjs.business", "com.alisonjs.persistence" })
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
