package com.sodiq.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheableTable {

    String name();

    boolean autoEvict() default false;

    long ttlMilliSeconds() default 0;
}
