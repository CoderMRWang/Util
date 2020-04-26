package com.wanghaotian.example.utils.mapsearch;

import lombok.Data;
/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/25
 * @modify By:
 */
@Data
public class CircularBaiduMapSearchObject extends BaseBaiduMapSearchObject{
    private String location;
    private static final int DEFAULT_RADIUS=1000;
    private int radius;//半径
    private boolean radius_limit;//是否严格

     CircularBaiduMapSearchObject(SEARCH_TYPE_ENUM searchType) {
        super(searchType);
    }
}
