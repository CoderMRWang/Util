package com.wanghaotian.example.utils;

import com.wanghaotian.example.object.Item;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * author;Wanghaotian
 * data:2020/4/12 0012
 */
public class PoolTestFactory implements PooledObjectFactory<Item> {
    //创建对象
    @Override
    public PooledObject<Item> makeObject() throws Exception {
        Item item=new Item();
        DefaultPooledObject<Item> defaultPooledObject=new DefaultPooledObject<>(item);
        return defaultPooledObject;
    }
    //销毁对象
    @Override
    public void destroyObject(PooledObject<Item> p) throws Exception {
        p.getObject();
    }
    //校验对象
    @Override
    public boolean validateObject(PooledObject<Item> p) {
        return false;
    }
    //对复用对象重新初始化
    @Override
    public void activateObject(PooledObject<Item> p) throws Exception {

    }
    //不初始化归还给池的对象
    @Override
    public void passivateObject(PooledObject<Item> p) throws Exception {

    }
}
