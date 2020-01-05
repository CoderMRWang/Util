package com.wanghaotian.example.utils;


import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/1/5
 * @modify By:
 */
public class ListCopyUtils<T,R> {
    /**
     * Description:
     * @author: wanghaotian
     * @date: 2020/1/5 13:22
     * @param:[target, resourceList]
     * @return:java.util.List<T>
     */
    public static <T,R> List<T> listCopyToAnotherList(Class<T> target, List<R> resourceList) throws IllegalAccessException, InstantiationException {
        if (resourceList.isEmpty()){
            return null;
        }else{
            List<T> resultList=new ArrayList<>(resourceList.size());
            for (R r:resourceList)
            {
                T instacne = target.newInstance();
                BeanUtils.copyProperties(r,instacne);
                resultList.add(instacne);
            }
            return resultList;
        }
    }
}
