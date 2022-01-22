package org.designer.cache.configuration.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Designer
 * @date : 2022/1/21 23:09
 */
@Getter
@Setter
@ConfigurationProperties(
        prefix = "spring.cache.redis"
)
public class RedisPropertiesExt {

    private List<Ext> expProperties;

    /**
     * @see CacheProperties
     */
    @Getter
    @Setter
    public static class Ext {
        private List<String> cacheNames = new ArrayList<>();
        private CacheProperties.Redis redis;
    }

}
