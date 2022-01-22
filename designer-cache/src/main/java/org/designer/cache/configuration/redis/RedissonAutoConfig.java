package org.designer.cache.configuration.redis;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.designer.cache.configuration.key.CacheKeyCreator;
import org.designer.cache.configuration.key.DefaultCacheKeyCreator;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


/**
 * @description: Redisson缓存
 * @author: Designer
 * @date : 2021/9/8 13:37
 * @see org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
 * @see org.redisson.spring.starter.RedissonAutoConfiguration
 */
@Configuration
@ConditionalOnClass({Redisson.class, RedisOperations.class})
@AutoConfigureBefore({RedisAutoConfiguration.class})
public class RedissonAutoConfig {

    @Bean
    @ConditionalOnMissingBean(CacheProperties.class)
    CacheProperties cacheProperties() {
        return new CacheProperties();
    }

    @Bean
    RedisPropertiesExt redisPropertiesExt() {
        return new RedisPropertiesExt();
    }

    /**
     * 缓存配置
     * RedisTemplate 只支持jackson序列化, 存入对象, 取出对象时全局统一返回String
     *
     * @param connectionFactory
     * @return
     * @see org.redisson.spring.starter.RedissonAutoConfiguration#redisTemplate(RedisConnectionFactory)
     * @see org.redisson.spring.starter.RedissonAutoConfiguration#stringRedisTemplate(RedisConnectionFactory)
     */
    @Bean
    RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory connectionFactory
            , RedisSerializer<Object> redisSerializer
    ) {
        // 构建RedisTemplate
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // 指定相应的序列化方案
        //default
        template.setDefaultSerializer(redisSerializer);
        //kv
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(redisSerializer);
        //hash
        template.setHashKeySerializer(RedisSerializer.string());
        template.setHashValueSerializer(redisSerializer);
        template.afterPropertiesSet();
        return template;
    }


    /**
     * 缓存key
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(CacheKeyCreator.class)
    CacheKeyCreator cacheKey() {
        return new DefaultCacheKeyCreator();
    }

    @Bean
    @ConditionalOnMissingBean(RedissonServiceImpl.class)
    RedissonService redissonService(
            RedissonClient redissonClient
            , CacheKeyCreator cacheKeyCreator
    ) {
        return new RedissonServiceImpl(redissonClient, cacheKeyCreator);
    }

    @Bean
    RedissonAutoConfigurationCustomizer redissonAutoConfigurationCustomizer(ObjectMapper objectMapper) {
        return configuration -> {
            configuration.setCodec(new JsonJacksonCodec(objectMapper));
        };
    }

    /**
     * @param redisSerializer
     * @param redisPropertiesExt
     * @return
     * @see org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     */
    @Bean
    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(
            RedisSerializer<Object> redisSerializer
            , RedisPropertiesExt redisPropertiesExt
    ) {
        return builder -> {
            RedisCacheManagerUtils.initRedisCacheManager(builder, redisPropertiesExt, redisSerializer);
        };
    }

    /**
     * 使用全局ObjectMapper作为序列化反序列化的的核心
     */
    @Configuration
    @ConditionalOnClass(value = Jackson2ObjectMapperBuilder.class)
    static class JacksonRedisSerializerConfig {

        @Bean
        @ConditionalOnMissingBean
        RedisSerializer<Object> redisSerializer(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
            Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
            jsonRedisSerializer.setObjectMapper(createObjectMapper(jackson2ObjectMapperBuilder));
            return jsonRedisSerializer;
        }

        /**
         * 序列化数据时会带入类信息, 这样可以自动反序列化
         *
         * @param jackson2ObjectMapperBuilder
         * @return
         * @see ObjectMapper#activateDefaultTyping(PolymorphicTypeValidator)
         */
        private ObjectMapper createObjectMapper(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
            ObjectMapper objectMapper = jackson2ObjectMapperBuilder.createXmlMapper(false).build();
            objectMapper.activateDefaultTyping(
                    LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY
            );
            return objectMapper;
        }

    }


}
