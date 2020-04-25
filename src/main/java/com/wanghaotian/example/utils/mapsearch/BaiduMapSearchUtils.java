package com.wanghaotian.example.utils.mapsearch;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.wanghaotian.example.utils.mapsearch.BaseBaiduMapSearchObject.SEARCH_TYPE_ENUM.*;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/24
 * @modify By:
 */
@Slf4j
public class BaiduMapSearchUtils {
    private static final int PLACE_TYPE = 1;
    private static final int CIRCULAR_TYPE = 2;
    private static final int RECTANGULAR_TYPE = 3;

    public static PlaceBaiduMapSearchObject getPlaceBaiduMapSearchObject() {
        return new PlaceBaiduMapSearchObject(PLACE);
    }

    public static CircularBaiduMapSearchObject getCircularBaiduMapSearchObject() {
        return new CircularBaiduMapSearchObject(CIRCULAR);
    }

    public static DetailsBaiduMapSearchObject getDetailsBaiduMapSearchObject() {
        return new DetailsBaiduMapSearchObject(DETAILS);
    }

    public static RectangularBaiduMapSearchObject getRectangularBaiduMapSearchObject() {
        return new RectangularBaiduMapSearchObject(RECTANGULAR);
    }


    public static String getResult(BaseBaiduMapSearchObject baseBaiduMapSearchObject) {
        StringBuilder stringBuilder = new StringBuilder();
        checkRequiredItems(baseBaiduMapSearchObject);
        if (CollectionUtils.isNotEmpty(baseBaiduMapSearchObject.getFilter())) {
            checkFilter(baseBaiduMapSearchObject, stringBuilder);
        }
        log.info("after filter string {}",stringBuilder.toString());
        return null;
    }


    /**
     * 校验查询必填项
     */
    private static boolean checkRequiredItems(BaseBaiduMapSearchObject baseBaiduMapSearchObject) {
        if (StringUtils.isNotBlank(baseBaiduMapSearchObject.getQuery()) && StringUtils.isNotBlank(baseBaiduMapSearchObject.getAk())) {
            boolean result = false;
            switch (baseBaiduMapSearchObject.getSearchType()) {
                case PLACE:
                    result = checkPlaceItem(baseBaiduMapSearchObject);
                    break;
                case CIRCULAR:
                    result = checkCircularItem(baseBaiduMapSearchObject);
                    break;
                case RECTANGULAR:
                    result = checkRectangleItem(baseBaiduMapSearchObject);
                    break;
                case DETAILS:
                    result = checkDetailsItem(baseBaiduMapSearchObject);
                    break;
                default:
            }
            if (result) {
                if (StringUtils.isNotBlank(baseBaiduMapSearchObject.getSn())) {
                    if (ObjectUtils.isNotEmpty(baseBaiduMapSearchObject.getTimestamp())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 校验地点查询
     */
    private static boolean checkPlaceItem(BaseBaiduMapSearchObject baseBaiduMapSearchObject) {
        PlaceBaiduMapSearchObject placeBaiduMapSearchObject = PlaceBaiduMapSearchObject.class.cast(baseBaiduMapSearchObject);
        return StringUtils.isNotBlank(placeBaiduMapSearchObject.getRegion());

    }

    /**
     * 校验圆形查询
     */
    private static boolean checkCircularItem(BaseBaiduMapSearchObject baseBaiduMapSearchObject) {
        CircularBaiduMapSearchObject circularBaiduMapSearchObject = CircularBaiduMapSearchObject.class.cast(baseBaiduMapSearchObject);
        return StringUtils.isNotBlank(circularBaiduMapSearchObject.getLocation());
    }

    /**
     * 校验矩形查询
     */
    private static boolean checkRectangleItem(BaseBaiduMapSearchObject baseBaiduMapSearchObject) {
        RectangularBaiduMapSearchObject rectangularMapSearchObject = RectangularBaiduMapSearchObject.class.cast(baseBaiduMapSearchObject);
        return StringUtils.isNotBlank(rectangularMapSearchObject.getBounds());
    }

    /**
     * 校验细节查询
     */
    private static boolean checkDetailsItem(BaseBaiduMapSearchObject baseBaiduMapSearchObject) {
        DetailsBaiduMapSearchObject detailsBaiduMapSearchObject = DetailsBaiduMapSearchObject.class.cast(baseBaiduMapSearchObject);
        return StringUtils.isNotBlank(detailsBaiduMapSearchObject.getUid()) && StringUtils.isNotBlank(detailsBaiduMapSearchObject.getUids());
    }


    /**
     * 校验过滤条件是否正确能否拼接
     */
    private static boolean checkFilter(BaseBaiduMapSearchObject baseBaiduMapSearchObject, StringBuilder stringBuilder) {
        List<Map<BaseBaiduMapSearchObject.INDUSTRY_TYPE_ENUM, BaseBaiduMapSearchObject.SORT_NAME_ENUM>> filterList = baseBaiduMapSearchObject.getFilter();
        for (Map<BaseBaiduMapSearchObject.INDUSTRY_TYPE_ENUM, BaseBaiduMapSearchObject.SORT_NAME_ENUM> map : filterList) {
            Iterator<Map.Entry<BaseBaiduMapSearchObject.INDUSTRY_TYPE_ENUM, BaseBaiduMapSearchObject.SORT_NAME_ENUM>> mapIt = map.entrySet().iterator();
            while (mapIt.hasNext()) {
                Map.Entry<BaseBaiduMapSearchObject.INDUSTRY_TYPE_ENUM, BaseBaiduMapSearchObject.SORT_NAME_ENUM> item = mapIt.next();
                BaseBaiduMapSearchObject.INDUSTRY_TYPE_ENUM key = item.getKey();
                BaseBaiduMapSearchObject.SORT_NAME_ENUM value = item.getValue();
                if (key.getType_key() == value.getType_key()) {
                    String keyType = key.getType();
                    String valueType = value.getType();
                    stringBuilder.append(keyType);
                    stringBuilder.append("=");
                    stringBuilder.append(valueType);
                    stringBuilder.append("&");
                } else {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 请求百度查询
     */
    private String httpRequestForBaiduMapAPI(String s) {

        return null;
    }


}
