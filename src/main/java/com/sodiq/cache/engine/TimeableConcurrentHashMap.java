package com.sodiq.cache.engine;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeableConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {

    private static final Logger logger = Logger.getLogger(TimeableConcurrentHashMap.class.getName());
    private Map<K, Long> timeMap = new ConcurrentHashMap<>();
    private long expiryInMillis = 0;

    public TimeableConcurrentHashMap() {
        initialize();
    }

    public TimeableConcurrentHashMap(long expiryInMillis) {
        this.expiryInMillis = expiryInMillis;
        initialize();
    }

    void initialize() {
        if (expiryInMillis > 0) {
            new CleanerThread().start();
        }
    }

    @Override
    public V put(K key, V value) {
        Date date = new Date();
        timeMap.put(key, date.getTime());
        V returnVal = super.put(key, value);
        return returnVal;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (K key : m.keySet()) {
            put(key, m.get(key));
        }
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if (!containsKey(key))
            return put(key, value);
        else
            return get(key);
    }

    class CleanerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                cleanMap();
                try {
                    Thread.sleep(expiryInMillis / 2);
                } catch (InterruptedException e) {
                    logger.log(Level.SEVERE, e, () -> "Error in cleaner thread...");
                }
            }
        }

        private void cleanMap() {
            long currentTime = new Date().getTime();
            for (K key : timeMap.keySet()) {
                if (currentTime > (timeMap.get(key) + expiryInMillis)) {
                    remove(key);
                    timeMap.remove(key);
                }
            }
        }
    }
}
