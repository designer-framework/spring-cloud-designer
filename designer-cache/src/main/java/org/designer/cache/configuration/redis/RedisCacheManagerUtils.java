package org.designer.cache.configuration.redis;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @description:
 * @author: Designer
 * @date : 2022/1/22 13:23
 */
public class RedisCacheManagerUtils {

    private RedisCacheManagerUtils() {
    }

    static void initRedisCacheManager(RedisCacheManager.RedisCacheManagerBuilder builder
            , RedisPropertiesExt redisPropertiesExt
            , RedisSerializer<Object> redisSerializer) {
        redisPropertiesExt.getExpProperties()
                .forEach(ext -> {
                    CacheProperties.Redis redis = ext.getRedis();
                    RedisCacheConfiguration cacheConfiguration = createConfiguration(redis, redisSerializer);
                    ext.getCacheNames()
                            .forEach(key -> {
                                builder.withCacheConfiguration(key, cacheConfiguration);
                            });
                });
    }


    private static org.springframework.data.redis.cache.RedisCacheConfiguration createConfiguration(
            CacheProperties.Redis redisProperties
            , RedisSerializer<Object> redisSerializer
    ) {
        org.springframework.data.redis.cache.RedisCacheConfiguration config = org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig();
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }

        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
        }

        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }

        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }

        if (redisSerializer != null) {
            config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer));
        }

        config = config.serializeKeysWith(
                RedisSerializationContext
                        .fromSerializer(RedisSerializer.string())
                        .getKeySerializationPair()
        );
        return config;
    }

}
