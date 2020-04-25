package com.wanghaotian.example;

import com.github.kevinsawicki.http.HttpRequest;
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
//        String responce= HttpRequest.get("http://api.map.baidu.com/place/v2/search?query=ATM机&tag=银行&region=北京&output=json&ak=GQg4czMTKd2csVGqYGCyTkAyxFDu08fL").body();
//        System.out.println(responce);


//        http://api.map.baidu.com/place/v2/search?query=ATM机&tag=银行&region=北京&output=json&ak=gqg4czmtkd2csvgqygcytkayxfdu08fl
//        http://api.map.baidu.com/place/v2/search?region=北京&citylimit=false&query=atm机&output=json&pagesize=40&pagenum=0&ak=gqg4czmtkd2csvgqygcytkayxfdu08fl

    }
}
