package com.sodiq.cache.service;

import com.sodiq.cache.engine.MemoryCacheEngine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheProvider {

    private static final Map<String, CacheProvider> INSTANCES = new ConcurrentHashMap<>();

    private final CacheEngine cacheEngine;

    private CacheProvider(CacheEngine cacheEngine) {
        this.cacheEngine = cacheEngine;
    }

    /**
     * Use a Multiton pattern to keep instances of cache engines. You can pass a different instance
     * of the CacheEngine interface to a different engine type
     *
     * @param cacheEngine
     * @return
     */
    public static synchronized CacheProvider createInstance(final CacheEngine cacheEngine) {
        String key = cacheEngine.getClass().toString();
        return INSTANCES.computeIfAbsent(key, v -> new CacheProvider(cacheEngine));
    }

    public static synchronized CacheProvider getInstance() {
        return createInstance(getDefaultEngine());
    }

    /**
     * Get a default instance of the cache engine to use
     *
     * @return CacheEngine
     */
    private static MemoryCacheEngine getDefaultEngine() {
        return new MemoryCacheEngine();
    }

    public CacheEngine getCacheEngine() {
        return cacheEngine;
    }
}
