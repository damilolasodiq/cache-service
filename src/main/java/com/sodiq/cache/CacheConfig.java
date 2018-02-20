package com.sodiq.cache;

public class CacheConfig {

    private boolean autoRemoval;

    private long timeToLiveSeconds;

    private String cacheName;

    public boolean isAutoRemoval() {
        return autoRemoval;
    }

    public void setAutoRemoval(boolean autoRemoval) {
        this.autoRemoval = autoRemoval;
    }

    public long getTimeToLiveSeconds() {
        return timeToLiveSeconds;
    }

    public void setTimeToLiveSeconds(long timeToLiveSeconds) {
        this.timeToLiveSeconds = timeToLiveSeconds;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }
}
