package com.wanghaotian.example;

import com.wanghaotian.example.utils.mapsearch.BaiduMapSearchUtils;
import com.wanghaotian.example.utils.mapsearch.PlaceBaiduMapSearchObject;

import java.util.ArrayList;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/25
 * @modify By:
 */
public class MapSearchTest {
    public static void main(String[] args) {
        PlaceBaiduMapSearchObject placeBaiduMapSearchObject = BaiduMapSearchUtils.getPlaceBaiduMapSearchObject();
        BaiduMapSearchUtils.getResult(placeBaiduMapSearchObject);
        ArrayList arrayList=new ArrayList(null);
        System.out.println(arrayList);

    }
}
