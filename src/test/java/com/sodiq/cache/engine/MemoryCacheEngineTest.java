package com.sodiq.cache.engine;

import com.sodiq.cache.service.CacheEngine;
import com.sodiq.cache.service.CacheProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MemoryCacheEngineTest {

    CacheEngine cacheEngine;

    String testStore;

    TestObject testObject;

    @Before
    public void setUp() throws Exception {
        this.testStore = "test";
        this.cacheEngine = CacheProvider.getInstance().getCacheEngine();
        this.testObject = new TestObject("some test data");
    }

    @Test
    public void performCacheOperations() {
        int testId = 23;
        this.cacheEngine.save(testId, this.testStore, this.testObject);
        Assert.assertTrue(this.cacheEngine.exists(testId, this.testStore));
        Assert.assertEquals(this.testObject.getData(), ((TestObject) this.cacheEngine.get(testId, this.testStore)).getData());
        this.cacheEngine.evict(testId, this.testStore);
        Assert.assertNull(this.cacheEngine.get(testId, this.testStore));
    }
}

class TestObject {
    String data;

    TestObject(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
