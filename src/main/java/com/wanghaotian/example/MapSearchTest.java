package com.wanghaotian.example;

import com.wanghaotian.example.utils.mapsearch.BaiduMapSearchUtils;
import com.wanghaotian.example.utils.mapsearch.BaseBaiduMapSearchObject;
import com.wanghaotian.example.utils.mapsearch.PlaceBaiduMapSearchObject;
import com.wanghaotian.example.utils.mapsearch.SortNameDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        placeBaiduMapSearchObject.setScope(BaseBaiduMapSearchObject.SCOPE_ENUM.DETILS);
        Map<BaseBaiduMapSearchObject.INDUSTRY_TYPE_ENUM, SortNameDetail> map=new HashMap<>();
        SortNameDetail sortNameDetail=new SortNameDetail();
        sortNameDetail.setSortName(BaseBaiduMapSearchObject.SORT_NAME_ENUM.HOTEL_LEVEL);
        Map<BaseBaiduMapSearchObject.SORT_RULE_ENUM,BaseBaiduMapSearchObject.SORT_CHOICE_ENUM> choiceEnumMap=new HashMap<>();
        choiceEnumMap.put(BaseBaiduMapSearchObject.SORT_RULE_ENUM.SORT_RULE, BaseBaiduMapSearchObject.SORT_CHOICE_ENUM.SORT_HIGHT_2_LOW);
        sortNameDetail.setChoice(choiceEnumMap);
        map.put(BaseBaiduMapSearchObject.INDUSTRY_TYPE_ENUM.HOTEL, sortNameDetail);
        placeBaiduMapSearchObject.getFilter().add(map);
        BaiduMapSearchUtils.getResult(placeBaiduMapSearchObject,PlaceBaiduMapSearchObject.class);
    }
}
