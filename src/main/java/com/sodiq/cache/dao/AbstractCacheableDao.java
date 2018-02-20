package com.sodiq.cache.dao;

import com.sodiq.cache.CacheConfig;
import com.sodiq.cache.CacheableTable;
import com.sodiq.cache.model.BaseEntity;
import com.sodiq.cache.service.CacheEngine;
import com.sodiq.cache.service.CacheProvider;
import com.sodiq.cache.service.ConfigurableCache;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractCacheableDao<T extends BaseEntity> implements GenericDao<T> {

    private final CacheEngine cacheEngine;
    private final Class<T> type;
    private boolean canUseCache;
    private CacheConfig cacheConfig;

    AbstractCacheableDao() {
        this.cacheEngine = CacheProvider.getInstance().getCacheEngine();
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        getCacheConfig();
        if (this.cacheEngine instanceof ConfigurableCache) {
            ((ConfigurableCache) this.cacheEngine).addConfiguration(cacheConfig);
        }
    }

    @Override
    public void save(T t) {
        //save to the regular repository
        if (canUseCache)
            cacheEngine.save(t.getId(), cacheConfig.getCacheName(), t);
    }

    private void getCacheConfig() {
        CacheableTable declaredAnnotation = this.type.getDeclaredAnnotation(CacheableTable.class);
        if (declaredAnnotation != null) {
            this.canUseCache = true;
            this.cacheConfig = new CacheConfig();
            this.cacheConfig.setCacheName(declaredAnnotation.name());
            if (declaredAnnotation.autoEvict()) {
                this.cacheConfig.setAutoRemoval(declaredAnnotation.autoEvict());
                this.cacheConfig.setTimeToLiveSeconds(declaredAnnotation.ttlMilliSeconds());
            }
        }
    }

    @Override
    public T get(long id) {
        if (canUseCache)
            return (T) cacheEngine.get(id, cacheConfig.getCacheName());
        else
            return null; //get from the main repo
    }

    @Override
    public void delete(long id) {
        //remove from repo then remove from cache
        if (canUseCache)
            cacheEngine.evict(id, cacheConfig.getCacheName());
    }
}
