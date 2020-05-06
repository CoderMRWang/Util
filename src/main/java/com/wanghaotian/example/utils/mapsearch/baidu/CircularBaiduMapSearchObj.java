package com.wanghaotian.example.utils.mapsearch.baidu;

import lombok.Data;
/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/25
 * @modify By:
 */
@Data
public class CircularBaiduMapSearchObj extends BaseBaiduMapSearchObj {
    private String location;
    private static final int DEFAULT_RADIUS=1000;
    private int radius;//半径
    private boolean radiusLimit;//是否严格

     CircularBaiduMapSearchObj(SEARCH_TYPE_ENUM searchType) {
        super(searchType);
    }
}
