package com.wanghaotian.example;

import com.wanghaotian.example.utils.mapsearch.BaiduMapSearchUtils;
import com.wanghaotian.example.utils.mapsearch.PlaceBaiduMapSearchObject;


/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/25
 * @modify By:
 */
public class MapSearchTest {
    public static void main(String[] args) {
        PlaceBaiduMapSearchObject placeBaiduMapSearchObject = BaiduMapSearchUtils.getPlaceBaiduMapSearchObject();
        placeBaiduMapSearchObject.setQuery("ATM机");
        placeBaiduMapSearchObject.setAk("GQg4czMTKd2csVGqYGCyTkAyxFDu08fL");
        placeBaiduMapSearchObject.setRegion("北京");
        placeBaiduMapSearchObject.setTag("银行");
        BaiduMapSearchUtils.getResult(placeBaiduMapSearchObject,PlaceBaiduMapSearchObject.class);
    }
}
