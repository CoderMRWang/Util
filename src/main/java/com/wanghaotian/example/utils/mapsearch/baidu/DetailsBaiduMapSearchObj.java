package com.wanghaotian.example.utils.mapsearch.baidu;
import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/25
 * @modify By:
 */
@Data
public class DetailsBaiduMapSearchObj extends BaseBaiduMapSearchObj {
    private String uid;
    private String uids;

     DetailsBaiduMapSearchObj(SEARCH_TYPE_ENUM searchType) {
        super(searchType);
    }
}
