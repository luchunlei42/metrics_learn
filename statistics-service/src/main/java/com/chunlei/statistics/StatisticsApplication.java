package com.chunlei.statistics;

import com.chunlei.statistics.repository.converter.DataPointIdReaderConverter;
import com.chunlei.statistics.repository.converter.DataPointIdWriterConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
@EnableOAuth2Client
@EnableFeignClients
@EnableHystrix
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration(exclude = {org.springframework.cloud.commons.security.ResourceServerTokenRelayAutoConfiguration.class})
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class,args);
    }

    @Configuration
    static class CustomConversionConfig{
        @Bean
        public CustomConversions customConversions(){
            return new CustomConversions(Arrays.asList(new DataPointIdReaderConverter(),
                    new DataPointIdWriterConverter()));
        }
    }
}
