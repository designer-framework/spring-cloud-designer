package org.designer.lock.service;

import org.designer.lock.annotation.Key;
import org.designer.lock.annotation.Lock;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @description:
 * @author: Designer
 * @date : 2021/10/19 23:19
 */
@Component
public class TestLockService {

    @Cacheable(cacheNames = "simple")
    @Lock(key = {@Key(name = "#p0")})
    public String lock1(Integer p0) {
        return String.valueOf(System.currentTimeMillis() + p0);
    }

    @Cacheable(cacheNames = "simples")
    @Lock(key = {@Key(name = "#p0")})
    public HashMap<String, Object> lock(Integer p0) {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("x", p0);
        return objectObjectHashMap;
    }

}
