package com.spdemo.Cache;

import java.util.concurrent.TimeUnit;

public interface CacheService {
    public void setCache(String key, String value, long timeout, TimeUnit timeUnit);

    public String getCache(String key);

    public String getCache(String key, Closure<String, String> closure);

    public String getCache(String key, Closure<String, String> closure, long timeout, TimeUnit timeUnit);

    public void deleteCache(String key);
}
