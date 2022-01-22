package org.designer.cache.configuration.redis;

import lombok.AllArgsConstructor;
import org.designer.cache.configuration.key.CacheKeyCreator;
import org.redisson.api.RBucket;
import org.redisson.api.RObject;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @description: Redisson缓存
 * @author: Designer
 * @date : 2021/9/8 13:37
 */
@AllArgsConstructor
public class RedissonServiceImpl implements RedissonService {

    private final RedissonClient redissonClient;

    private final CacheKeyCreator cacheKeyCreator;

    @Override
    public void save(String prefix, String key, Object val, long timeout, TimeUnit timeUnit) {
        redissonConsumer(prefix, key, bucket -> {
            bucket.set(val, timeout, timeUnit);
        });
    }

    @Override
    public void del(String prefix, String key) {
        redissonConsumer(prefix, key, RObject::delete);
    }

    private void redissonConsumer(String prefix, String key, Consumer<RBucket<Object>> bucketVoidFunction) {
        bucketVoidFunction.accept(getBucket(cacheKeyCreator.create(prefix, key)));
    }

    private RBucket<Object> getBucket(String businessName) {
        return redissonClient.getBucket(businessName);
    }

}
