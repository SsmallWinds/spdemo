package com.spdemo.Cache;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    Cache<String, Object> caffeineCache;

    public void setCache(String key, String value, long timeout, TimeUnit timeUnit) {
        caffeineCache.put(key, value);
    }

    public String getCache(String key) {
        return doGetCache(key);
    }

    @Override
    public String getCache(String key, Closure<String, String> closure) {
        return doGetCache(key, closure);
    }

    @Override
    public String getCache(String key, Closure<String, String> closure, long timeout, TimeUnit timeUnit) {
        return doGetCache(key, closure);
    }

    public void deleteCache(String key) {
        caffeineCache.asMap().remove(key);
    }

    private String doGetCache(String key) {
        caffeineCache.getIfPresent(key);
        return (String) caffeineCache.asMap().get(key);
    }

    private String doGetCache(String key, Closure<String, String> closure) {
        String val = doGetCache(key);
        if (val == null) {
            val = closure.execute(key);
            setCache(key, val, 0, TimeUnit.SECONDS);
        }
        return val;
    }

}
