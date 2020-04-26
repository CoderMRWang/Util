package com.wanghaotian.example.utils.mapsearch;
import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/25
 * @modify By:
 */
@Data
public class DetailsBaiduMapSearchObject extends BaseBaiduMapSearchObject{
    private String uid;
    private String uids;

     DetailsBaiduMapSearchObject(SEARCH_TYPE_ENUM searchType) {
        super(searchType);
    }
}
