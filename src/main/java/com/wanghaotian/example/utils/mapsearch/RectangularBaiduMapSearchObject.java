package com.wanghaotian.example.utils.mapsearch;

import lombok.Data;
/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/25
 * @modify By:
 */
@Data
public class RectangularBaiduMapSearchObject extends BaseBaiduMapSearchObject {
    private String bounds;//坐标39.915,116.404,39.975,116.414

     RectangularBaiduMapSearchObject(SEARCH_TYPE_ENUM searchType) {
        super(searchType);
    }
}
