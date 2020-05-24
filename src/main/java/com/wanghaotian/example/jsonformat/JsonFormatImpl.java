package com.wanghaotian.example.jsonformat;

import com.wanghaotian.example.object.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/5/16
 * @modify By:
 */
public class JsonFormatImpl extends AbstractJsonFormat<Item>{
    public JsonFormatImpl(){};

    @Override
    protected List<Item> work() {
        List<Item> list=new ArrayList<>();
        Item item=new Item();
        item.setBrand("a");
        item.setCategory("c");
        item.setId(1L);
        item.setImages("b");
        list.add(item);
        return list;
    }

    public static void main(String[] args) {
        JsonFormatImpl jsonFormat=new JsonFormatImpl();
        jsonFormat.doWork();
    }
}
