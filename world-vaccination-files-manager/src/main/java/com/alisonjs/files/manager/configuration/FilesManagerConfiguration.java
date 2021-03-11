package com.alisonjs.files.manager.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "classpath:/files-manager-application.properties", "classpath:/application.properties" },
        ignoreResourceNotFound = true)
public class FilesManagerConfiguration {
}
