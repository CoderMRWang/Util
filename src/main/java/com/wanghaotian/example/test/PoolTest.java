package com.wanghaotian.example.test;

import com.wanghaotian.example.object.Item;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * author;Wanghaotian
 * data:2020/4/12 0012
 */
public class PoolTest extends GenericObjectPool<Item> {

    public PoolTest(PooledObjectFactory<Item> factory) {
        super(factory);
    }

    public PoolTest(PooledObjectFactory<Item> factory, GenericObjectPoolConfig<Item> config) {
        super(factory, config);
    }

    public PoolTest(PooledObjectFactory<Item> factory, GenericObjectPoolConfig<Item> config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }
}
