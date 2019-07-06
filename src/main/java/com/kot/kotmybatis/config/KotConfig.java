package com.kot.kotmybatis.config;


import kot.bootstarter.kotmybatis.common.id.IdGenerator;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KotConfig {

    @Bean("idGenerator")
    public IdGenerator idGenerator() {
        return RandomUtils::nextLong;
    }
}
