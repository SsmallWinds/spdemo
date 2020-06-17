package com.spdemo.Cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public Cache<String, Object> caffeineCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .initialCapacity(100)
                .maximumSize(1000).build();
    }
}
