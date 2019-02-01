package com.group4.barrageSearch;

import com.group4.barrageSearch.util.RedisObjectSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    /**
     * spring boot 2.0.x以上版本的使用方式
     * @param redisConnectionFactory
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheManager rcm = RedisCacheManager.builder(redisConnectionFactory).withInitialCacheConfigurations(singletonMap
                ("results", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(300L))
                        .disableCachingNullValues())).withInitialCacheConfigurations(singletonMap
                ("videoInfos", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(7200L))
                        .disableCachingNullValues())).transactionAware().build();
        // 设置缓存的过期时间
        return rcm;
    }

    /**
     * 自定义生成redis-key
     *  lambda表达式
     * @return
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, objects)->{
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName()).append(".");
            sb.append(method.getName()).append(".");
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            System.out.println("keyGenerator=" + sb.toString());
            return sb.toString();
        };

    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);//注入redis数据源
        template.setKeySerializer(new StringRedisSerializer());//设置key的序列化方式
        template.setValueSerializer(new RedisObjectSerializer());//设置value的序列化方式
        return template;
    }
}