package org.designer.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.designer.cache.test.entity.Obj;
import org.designer.cache.test.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.redisson.spring.starter.RedissonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.Ordered;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.Map;

/**
 * @description:
 * @author: Designer
 * @date : 2021/9/18 13:39
 */
//将缓存注解顺序优先级+1, 缓存中有值的话直接返回, 不会再走后续@Lock的逻辑
@EnableCaching(order = Ordered.LOWEST_PRECEDENCE - 1, proxyTargetClass = true)
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CacheTestApplication.class)
@SpringBootApplication
public class CacheTestApplication {
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private TestService testService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedissonProperties redissonProperties;
    @Autowired
    private RedisProperties redisProperties;
    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Autowired
    private ObjectMapper objectMapper;
    /**
     * 配置
     */
    private Config config;

    private RedissonAutoConfiguration redissonAutoConfiguration = new RedissonAutoConfiguration();


    /**
     * Redisson用于分布式锁
     * RedisTemplate用于存对象
     */
    @Test
    public void testCache() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        Obj obj = new Obj();
        obj.setId(100L);
        obj.setStr("lock");
        System.out.println("1-调用service之前的入参[未传time参数]: " + obj);
        Obj obj1 = testService.testCache(obj);
        System.out.println("1-调用service之后的输出: " + obj1);
        //
        obj.setTime(null);
        System.out.println("2-调用service之前的入参[未传time参数]: " + obj);
        Obj obj2 = testService.testCache(obj);
        System.out.println("2-调用service之后的输出: " + obj2);
        System.out.println(cacheNames);
        Map<String, String> obj3 = testService.testCache("obj");
        Map<String, String> obj4 = testService.testCache("obj");
    }


}
