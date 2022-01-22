package org.designer.cache.test.config;

import org.designer.cache.test.entity.Obj;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @description:
 * @author: Designer
 * @date : 2021/9/18 13:39
 */
@Configuration
public class CacheConfig {

    /**
     * 每个缓存注解都要设置一个RedisCacheManagerBuilderCustomizer
     *
     * @return
     */
    //@Bean
    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(
            RedisConnectionFactory connectionFactory
    ) {
        return new RedisCacheManagerBuilderCustomizer() {
            @Override
            public void customize(RedisCacheManager.RedisCacheManagerBuilder builder) {
                RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                        .defaultCacheConfig()
                        .entryTtl(Duration.of(10, ChronoUnit.SECONDS))
                        .serializeValuesWith(
                                RedisSerializationContext.SerializationPair
                                        .fromSerializer(new Jackson2JsonRedisSerializer<>(Obj.class))
                        );
                builder.cacheWriter(
                                RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
                        .withCacheConfiguration("simple", redisCacheConfiguration
                        );
            }
        };
    }

}
