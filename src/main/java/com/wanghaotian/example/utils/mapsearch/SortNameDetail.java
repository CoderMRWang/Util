package com.wanghaotian.example.utils.mapsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/26
 * @modify By:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortNameDetail {
    private BaseBaiduMapSearchObject.SORT_NAME_ENUM sortName;
    private Map<BaseBaiduMapSearchObject.SORT_RULE_ENUM,BaseBaiduMapSearchObject.SORT_CHOICE_ENUM> choice;

}
