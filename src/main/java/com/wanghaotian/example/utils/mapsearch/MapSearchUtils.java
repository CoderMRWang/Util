package com.wanghaotian.example.utils.mapsearch;


import com.wanghaotian.example.utils.mapsearch.baidu.BaiduMapSearchUtils;
import com.wanghaotian.example.utils.mapsearch.gaode.GaoDeMapSearchUtils;
import com.wanghaotian.example.utils.mapsearch.google.GoogleMapSearchUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
@Slf4j
public class MapSearchUtils {

    public enum OUT_PUT_ENUM{
        JSON("json"),XML("xml");

        OUT_PUT_ENUM(String value) {
            this.value = value;
        }

        private String value;
    }

    private MapSearchUtils() {
    }

    public static String getRequestUrlPara(Object searchObject, Class clazz, List<String> ignoreFileds) {

        if (ObjectUtils.isNotEmpty(searchObject)) {
            StringBuilder stringBuilder = new StringBuilder();
            ArrayList<Field> fieldsList = new ArrayList<>();
            Field[] superClassFields = clazz.getSuperclass().getDeclaredFields();
            Field[] fields = clazz.getDeclaredFields();
            fieldsList.addAll(Arrays.asList(fields));
            fieldsList.addAll(Arrays.asList(superClassFields));
            for (Field field : fieldsList) {
                field.setAccessible(true);
                if (!ignoreFileds.contains(field.getName())) {
                    {
                        try {
                            if (ObjectUtils.isNotEmpty(field.get(searchObject))) {
                                stringBuilder.append(field.getName());
                                stringBuilder.append("=");
                                stringBuilder.append(field.get(searchObject));
                                stringBuilder.append("&");
                            }
                        } catch (IllegalAccessException e) {
                            log.info("方法{}发生异常:{}", "setUpRequest", e.getMessage());
                        }
                    }
                }
            }
             return stringBuilder.substring(0,stringBuilder.length()-1).trim();
        }else{
            return null;
        }

    }
        public static BaiduMapSearchUtils getBaiduMapSearchUtils(){
            return new BaiduMapSearchUtils();
        }
        public static GaoDeMapSearchUtils getGaoDeMapSearchUtils(){
            return new GaoDeMapSearchUtils();
        }
        public static GoogleMapSearchUtils getGoogleMapSearchUtils(){
            return new GoogleMapSearchUtils();
        }

    }
