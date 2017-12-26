package com.qs.mvc.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:env.properties")
public class RedisConfiguration {

    @Value("${redis.common.host}")
    private String hostName;

    @Value("${redis.common.port}")
    private int commonPort;

    @Value("${redis.session.port}")
    private int sessionPort;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean
    public JedisConnectionFactory getCommonJedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig());
        //设置redis连接host、port
        jedisConnectionFactory.setHostName(hostName);
        jedisConnectionFactory.setPort(commonPort);
        jedisConnectionFactory.setUsePool(true);
        return jedisConnectionFactory;
    }

    @Bean(name = "commonRedisTemplate")
    public RedisTemplate getCommonRedisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(getCommonJedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public JedisConnectionFactory getSessionJedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig());
        //设置redis连接host、port
        jedisConnectionFactory.setHostName(hostName);
        jedisConnectionFactory.setPort(sessionPort);
        jedisConnectionFactory.setUsePool(true);
        return jedisConnectionFactory;
    }

    @Bean(name = "sessionRedisTemplate")
    public RedisTemplate getSessionRedisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(getSessionJedisConnectionFactory());
        return redisTemplate;
    }


    /**
     * 使用@Value注解 需要注入propertySourcesPlaceholderConfigurer对象（spring3.0之后推荐使用）
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
