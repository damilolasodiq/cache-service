package com.sodiq.cache.dao;

import com.sodiq.cache.model.BaseEntity;

public interface GenericDao<T extends BaseEntity> {

    void save(T t);

    T get(long id);

    void delete(long id);
}
