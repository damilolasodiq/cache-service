package com.sodiq.cache.service;

public interface CacheEngine {

    void save(long key, String cacheName, Object obj);

    Object get(long key, String cacheName);

    void evict(long key, String cacheName);

    boolean exists(long key, String cacheName);
}
