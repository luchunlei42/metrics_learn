package com.chunlei.auth.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.chunlei.auth.dao")
public class MybatisConfig {
}
