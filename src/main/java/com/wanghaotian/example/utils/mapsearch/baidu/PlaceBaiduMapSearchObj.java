package com.wanghaotian.example.utils.mapsearch.baidu;

import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/25
 * @modify By:
 */
@Data
public class PlaceBaiduMapSearchObj extends BaseBaiduMapSearchObj {
    private String region;//检索行政区划区域,必选
    private boolean cityLimit;//区域数据召回限制，为true时，仅召回region对应区域内数据。可选

     PlaceBaiduMapSearchObj(SEARCH_TYPE_ENUM searchType) {
        super(searchType);
    }
}
