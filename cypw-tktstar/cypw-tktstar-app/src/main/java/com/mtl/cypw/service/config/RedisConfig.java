package com.mtl.cypw.service.config;

import com.mtl.cypw.common.redis.config.RedisProperties;
import com.mtl.cypw.common.redis.template.CommonRedisTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tang.
 * @date 2020/02/14.
 */
@Configuration
public class RedisConfig {

    @Bean
    @ConfigurationProperties(prefix = "common.redis")
    public RedisProperties redisProperties() {
        return new RedisProperties();
    }

    @Bean
    public CommonRedisTemplate commonRedisTemplate() {
        CommonRedisTemplate redisTemplate = new CommonRedisTemplate();
        redisTemplate.setRedisProperties(redisProperties());
        return redisTemplate;
    }

}
