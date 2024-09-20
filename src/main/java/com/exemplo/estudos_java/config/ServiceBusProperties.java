package com.exemplo.estudos_java.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "azure.servicebus")
public class ServiceBusProperties {
    private String connectionString;
    private String queueName1;
    private String queueName2;
}
