package org.designer.cache.configuration;

import java.util.concurrent.TimeUnit;

/**
 * @description: Redisson缓存
 * @author: Designer
 * @date : 2021/9/8 13:37
 */
public interface CacheService {

    void save(String prefix, String key, Object val, long timeout, TimeUnit timeUnit);

    void del(String prefix, String key);

}
