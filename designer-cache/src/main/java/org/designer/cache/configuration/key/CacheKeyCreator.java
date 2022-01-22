package org.designer.cache.configuration.key;

/**
 * @description:
 * @author: Designer
 * @date : 2021/9/8 13:36
 */
public interface CacheKeyCreator {

    /**
     * 按照规则生成新的缓存key
     *
     * @param key 缓存key
     * @return
     */
    String create(String key);

    /**
     * 按照规则生成新的缓存key
     *
     * @param prefix 前缀
     * @param key    缓存key
     * @return
     */
    String create(String prefix, String key);

}
