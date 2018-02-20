package com.sodiq.cache.engine;

import com.sodiq.cache.CacheConfig;
import com.sodiq.cache.service.CacheEngine;
import com.sodiq.cache.service.ConfigurableCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryCacheEngine implements CacheEngine, ConfigurableCache {

    private static Map<String, Map<Long, Object>> engine = new ConcurrentHashMap<>();

    private List<CacheConfig> configurations = new ArrayList<>();

    @Override
    public List<CacheConfig> getConfigurations() {
        return this.configurations;
    }

    @Override
    public void setConfiguration(List<CacheConfig> configurations) {
        this.configurations = configurations;
    }

    @Override
    public void addConfiguration(CacheConfig configuration) {
        if (this.configurations == null) {
            this.configurations = new ArrayList<>();
        }
        this.configurations.add(configuration);
    }

    @Override
    public void save(long key, String cacheName, Object obj) {
        engine.computeIfAbsent(cacheName, v -> {
            Optional<CacheConfig> first = configurations.stream().filter(c -> cacheName.equalsIgnoreCase(c.getCacheName()) && c.isAutoRemoval()).findFirst();
            if (first.isPresent()) {
                CacheConfig cacheConfig = first.get();
                return new TimeableConcurrentHashMap(cacheConfig.getTimeToLiveSeconds());
            } else {
                return new TimeableConcurrentHashMap<>();
            }
        }).put(key, obj);
    }

    @Override
    public Object get(long key, String cacheName) {
        Map<Long, Object> cache = engine.get(cacheName);
        if (cache != null) {
            return cache.get(key);
        }
        return null;
    }

    @Override
    public void evict(long key, String cacheName) {
        Map<Long, Object> cache = engine.get(cacheName);
        if (cache != null) {
            cache.remove(key);
        }
    }

    @Override
    public boolean exists(long key, String cacheName) {
        return engine.containsKey(cacheName) && engine.get(cacheName).containsKey(key);
    }
}
