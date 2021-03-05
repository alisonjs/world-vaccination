package com.alisonjs.persistence.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(
        value = {"classpath:/persistence-application.properties", "classpath:/application.properties" },
        ignoreResourceNotFound = true)
@ComponentScan("com.alisonjs.persistence")
@EntityScan(basePackages = "com.alisonjs.persistence.entity")
@EnableJpaRepositories(basePackages = { "com.alisonjs.persistence.repository" })
@EnableTransactionManagement
public class PersistenceConfiguration {
}
