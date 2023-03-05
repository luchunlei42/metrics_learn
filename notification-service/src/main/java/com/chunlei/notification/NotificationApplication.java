package com.chunlei.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableOAuth2Client
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableScheduling
@EnableHystrix
@EnableAutoConfiguration(exclude = {org.springframework.cloud.commons.security.ResourceServerTokenRelayAutoConfiguration.class})
public class NotificationApplication {
    public static void main(String[] args) {

        SpringApplication.run(NotificationApplication.class,args);
    }
}
