package com.wanghaotian.example.jsonformat;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.wanghaotian.example.object.Item;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/5/16
 * @modify By:
 */
@Slf4j
@Data
public abstract class AbstractJsonFormat<T> {
    protected AbstractJsonFormat(){}

    private T formatJsonArray(String jsonArray){
        Type genericSuperclass=this.getClass().getGenericSuperclass();
        Type[] types=((ParameterizedType)genericSuperclass).getActualTypeArguments();
        Class<T> clazz=(Class)types[0];
        List<T> list=JSONArray.parseArray(jsonArray,clazz);
        log.info("{}",list);
        return null;
    }
    protected abstract List<T> work();

    public void doWork(){
        String workString=work().toString();
        formatJsonArray(JSON.parseArray(workString).toJSONString());
    }



}
