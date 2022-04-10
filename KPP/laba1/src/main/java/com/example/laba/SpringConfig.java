package com.example.laba;
import com.example.laba.cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringConfig {
    @Bean("cache")
    @Scope(value = "singleton")
    Cache cache() {
        return new Cache();
    }
}