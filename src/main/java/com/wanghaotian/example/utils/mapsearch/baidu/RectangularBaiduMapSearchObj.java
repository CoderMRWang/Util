package com.wanghaotian.example.utils.mapsearch.baidu;

import lombok.Data;
/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/25
 * @modify By:
 */
@Data
public class RectangularBaiduMapSearchObj extends BaseBaiduMapSearchObj {
    private String bounds;//坐标39.915,116.404,39.975,116.414

     RectangularBaiduMapSearchObj(SEARCH_TYPE_ENUM searchType) {
        super(searchType);
    }
}
