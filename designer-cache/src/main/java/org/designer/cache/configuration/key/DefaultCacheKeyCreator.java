package org.designer.cache.configuration.key;

import org.springframework.beans.factory.annotation.Value;

/**
 * 缓存key
 *
 * @description: Redisson缓存
 * @author: Designer
 * @date : 2021/9/8 13:37
 */
public class DefaultCacheKeyCreator implements CacheKeyCreator {

    /**
     * 应用名称
     */
    @Value("${spring.application.name}")
    private String appName;

    /**
     * 创建key
     */
    @Override
    public String create(String key) {
        return (appName + ":" + key).toLowerCase();
    }

    /**
     * 创建key
     *
     * @param prefix 前缀
     * @param key    键
     * @return 键
     */
    @Override
    public String create(String prefix, String key) {
        return (appName + ":" + prefix + ":" + key).toLowerCase();
    }

}
