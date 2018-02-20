package com.sodiq.cache.service;

import com.sodiq.cache.CacheConfig;

import java.util.List;

public interface ConfigurableCache {

    List<CacheConfig> getConfigurations();

    void setConfiguration(List<CacheConfig> configurations);

    void addConfiguration(CacheConfig configuration);

}
