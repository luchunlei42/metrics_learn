package com.chunlei.account.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.chunlei.account.dao")
public class MybatisConfig {
}
