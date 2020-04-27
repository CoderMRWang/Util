package com.wanghaotian.example.utils.mapsearch.baidu;

import com.github.kevinsawicki.http.HttpRequest;
import com.wanghaotian.example.utils.mapsearch.MapSearchUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Field;
import java.util.*;
import static com.wanghaotian.example.utils.mapsearch.baidu.BaseBaiduMapSearchObj.SEARCH_TYPE_ENUM.*;
import static com.wanghaotian.example.utils.mapsearch.baidu.FieldCheckError.*;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/24
 * @modify By:
 */
@Slf4j
public class BaiduMapSearchUtils extends MapSearchUtils {
    private static final String DETAILS_PREFIX = "http://api.map.baidu.com/place/v2/detail?";
    private static final String CIRCULAR_PREFIX = "http://api.map.baidu.com/place/v2/search?";
    private static final String PLACE_PREFIX = "http://api.map.baidu.com/place/v2/search?";
    private static final String RECTANGULAR_PREFIX = "http://api.map.baidu.com/place/v2/search?";
    private static final String FILTER = "filter";
    private static final String SEARCH_TYPE = "searchType";
    private static final String DETAILS_BAIDU_SEARCHOBJECT_NAME = "com.wanghaotian.example.utils.mapsearch.DetailsBaiduMapSearchObject";
    private static final String CIRCULAR_BAIDU_SEARCHOBJECT_NAME = "com.wanghaotian.example.utils.mapsearch.CircularBaiduMapSearchObject";
    private static final String PLACE_BAIDU_SEARCHOBJECT_NAME = "com.wanghaotian.example.utils.mapsearch.PlaceBaiduMapSearchObject";
    private static final String RECTANGULAR_BAIDU_SEARCHOBJECT_NAME = "com.wanghaotian.example.utils.mapsearch.RectangularBaiduMapSearchObject";

    public static PlaceBaiduMapSearchObj getPlaceBaiduMapSearchObject() {
        return new PlaceBaiduMapSearchObj(PLACE);
    }

    public static CircularBaiduMapSearchObj getCircularBaiduMapSearchObject() {
        return new CircularBaiduMapSearchObj(CIRCULAR);
    }

    public static DetailsBaiduMapSearchObj getDetailsBaiduMapSearchObject() {
        return new DetailsBaiduMapSearchObj(DETAILS);
    }

    public static RectangularBaiduMapSearchObj getRectangularBaiduMapSearchObject() {
        return new RectangularBaiduMapSearchObj(RECTANGULAR);
    }


    public static String getResult(BaseBaiduMapSearchObj baseBaiduMapSearchObject, Class clazz) {
        StringBuilder errorString = new StringBuilder();
        String responce = null;
        if (checkRequiredItems(baseBaiduMapSearchObject, errorString)) {
            String request = setUpRequest(baseBaiduMapSearchObject, clazz);
            if (CollectionUtils.isNotEmpty(baseBaiduMapSearchObject.getFilter())) {
                StringBuilder requestBuilder = new StringBuilder();
                checkFilter(baseBaiduMapSearchObject, requestBuilder);
                log.info("after filter string {}", requestBuilder.toString());
                request += "&" + requestBuilder;
            }
            log.info("{}", request);
            responce = doRequest(request);
            log.info("返回的结果:{}", responce);
        } else {
            log.info("{}", errorString);
        }

        return responce;
    }


    /**
     * 校验查询必填项
     */
    private static boolean checkRequiredItems(BaseBaiduMapSearchObj baseBaiduMapSearchObject, StringBuilder stringBuilder) {
        int okCount = 0;
        if (StringUtils.isBlank(baseBaiduMapSearchObject.getQuery())) {
            okCount -= 1;
            stringBuilder.append(FIELD_QUERY_NULL);
        }
        if (StringUtils.isBlank(baseBaiduMapSearchObject.getAk())) {
            okCount -= 1;
            stringBuilder.append(FIELD_AK_NULL);
        }
        if (StringUtils.isNotBlank(baseBaiduMapSearchObject.getSn())) {
            if (ObjectUtils.isEmpty(baseBaiduMapSearchObject.getTimestamp())) {
                okCount -= 1;
                stringBuilder.append(FIELD_TIMESTAMP_NULL);
            }
        }
        if (CollectionUtils.isNotEmpty(baseBaiduMapSearchObject.getFilter())) {
            if (!BaseBaiduMapSearchObj.SCOPE_ENUM.DETILS.equals(baseBaiduMapSearchObject.getScope())) {
                okCount -= 1;
                stringBuilder.append(FIELD_SCOPE_ERROE);
            }
        }

        return (okCount == 0) && checkSpicalItem(baseBaiduMapSearchObject, stringBuilder);
    }

    private static boolean checkSpicalItem(BaseBaiduMapSearchObj baseBaiduMapSearchObject, StringBuilder stringBuilder) {
        boolean result = false;
        switch (baseBaiduMapSearchObject.getSearchType()) {
            case PLACE:
                result = checkPlaceItem(baseBaiduMapSearchObject, stringBuilder);
                break;
            case CIRCULAR:
                result = checkCircularItem(baseBaiduMapSearchObject, stringBuilder);
                break;
            case RECTANGULAR:
                result = checkRectangleItem(baseBaiduMapSearchObject, stringBuilder);
                break;
            case DETAILS:
                result = checkDetailsItem(baseBaiduMapSearchObject, stringBuilder);
                break;
            default:
        }
        return result;
    }


    /**
     * 校验地点查询
     */
    private static boolean checkPlaceItem(BaseBaiduMapSearchObj baseBaiduMapSearchObject, StringBuilder stringBuilder) {
        int okSize = 0;
        PlaceBaiduMapSearchObj placeBaiduMapSearchObject = PlaceBaiduMapSearchObj.class.cast(baseBaiduMapSearchObject);
        if (StringUtils.isBlank(placeBaiduMapSearchObject.getRegion())) {
            stringBuilder.append(FIELD_REGION_NULL);
            okSize -= 1;
        }
        return okSize == 0;
    }

    /**
     * 校验圆形查询
     */
    private static boolean checkCircularItem(BaseBaiduMapSearchObj baseBaiduMapSearchObject, StringBuilder stringBuilder) {
        int okSize = 0;
        CircularBaiduMapSearchObj circularBaiduMapSearchObject = CircularBaiduMapSearchObj.class.cast(baseBaiduMapSearchObject);
        if (StringUtils.isBlank(circularBaiduMapSearchObject.getLocation())) {
            stringBuilder.append(FIELD_LOCATION_NULL);
            okSize -= 1;
        }
        return okSize == 0;
    }

    /**
     * 校验矩形查询
     */
    private static boolean checkRectangleItem(BaseBaiduMapSearchObj baseBaiduMapSearchObject, StringBuilder stringBuilder) {
        RectangularBaiduMapSearchObj rectangularMapSearchObject = RectangularBaiduMapSearchObj.class.cast(baseBaiduMapSearchObject);
        int okSize = 0;
        if (StringUtils.isBlank(rectangularMapSearchObject.getBounds())) {
            stringBuilder.append(FIELD_BOUND_NULL);
            okSize -= 1;
        }
        return okSize == 0;
    }

    /**
     * 校验细节查询
     */
    private static boolean checkDetailsItem(BaseBaiduMapSearchObj baseBaiduMapSearchObject, StringBuilder stringBuilder) {
        DetailsBaiduMapSearchObj detailsBaiduMapSearchObject = DetailsBaiduMapSearchObj.class.cast(baseBaiduMapSearchObject);
        int okSize = 0;
        if (StringUtils.isBlank(detailsBaiduMapSearchObject.getUid())) {
            okSize -= 1;
            stringBuilder.append(FIELD_UID_NULL);
        }
        if (StringUtils.isBlank(detailsBaiduMapSearchObject.getUids())) {
            okSize -= 1;
            stringBuilder.append(FIELD_UIDS_NULL);
        }
        return okSize == 0;
    }


    /**
     * 校验过滤条件是否正确能否拼接
     */
    private static boolean checkFilter(BaseBaiduMapSearchObj baseBaiduMapSearchObject, StringBuilder stringBuilder) {
        List<Map<BaseBaiduMapSearchObj.INDUSTRY_TYPE_ENUM, SortNameDetail>> filterList = baseBaiduMapSearchObject.getFilter();
        for (Map<BaseBaiduMapSearchObj.INDUSTRY_TYPE_ENUM, SortNameDetail> map : filterList) {
            Iterator<Map.Entry<BaseBaiduMapSearchObj.INDUSTRY_TYPE_ENUM, SortNameDetail>> mapIt = map.entrySet().iterator();
            while (mapIt.hasNext()) {
                Map.Entry<BaseBaiduMapSearchObj.INDUSTRY_TYPE_ENUM, SortNameDetail> item = mapIt.next();
                BaseBaiduMapSearchObj.INDUSTRY_TYPE_ENUM key = item.getKey();
                SortNameDetail sortNameDetail = item.getValue();
                BaseBaiduMapSearchObj.SORT_NAME_ENUM value = sortNameDetail.getSortName();
                if (key.getType_key() == value.getType_key()) {
                    String valueType = value.getType();
                    stringBuilder.append("industry_type=");
                    stringBuilder.append(key + "&");
                    stringBuilder.append("sort_name:");
                    stringBuilder.append(valueType);
                    if (ObjectUtils.isNotEmpty(sortNameDetail.getChoice())) {
                        Set<Map.Entry<BaseBaiduMapSearchObj.SORT_RULE_ENUM, BaseBaiduMapSearchObj.SORT_CHOICE_ENUM>> choiceSet = sortNameDetail.getChoice().entrySet();
                        Iterator<Map.Entry<BaseBaiduMapSearchObj.SORT_RULE_ENUM, BaseBaiduMapSearchObj.SORT_CHOICE_ENUM>> iterator = choiceSet.iterator();
                        while (iterator.hasNext()) {
                            Map.Entry<BaseBaiduMapSearchObj.SORT_RULE_ENUM, BaseBaiduMapSearchObj.SORT_CHOICE_ENUM> entry = iterator.next();
                            stringBuilder.append("|");
                            stringBuilder.append(entry.getKey());
                            stringBuilder.append(":");
                            stringBuilder.append(entry.getValue());
                        }
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 组装request请求体
     */
    private static String setUpRequest(BaseBaiduMapSearchObj baseBaiduMapSearchObject, Class<? extends BaseBaiduMapSearchObj> clazz) {
        StringBuilder stringBuilder = setUpRequestPrefix(clazz);
        ArrayList<Field> fieldsList = new ArrayList<>();
        Field[] baseBaiduMapFields = clazz.getSuperclass().getDeclaredFields();
        Field[] fields = clazz.getDeclaredFields();
        fieldsList.addAll(Arrays.asList(fields));
        fieldsList.addAll(Arrays.asList(baseBaiduMapFields));
        for (Field field : fieldsList) {
            field.setAccessible(true);
            if (!FILTER.equals(field.getName()) && !SEARCH_TYPE.equals(field.getName())) {
                try {
                    if (ObjectUtils.isNotEmpty(field.get(baseBaiduMapSearchObject))) {
                        stringBuilder.append(field.getName());
                        stringBuilder.append("=");
                        stringBuilder.append(field.get(baseBaiduMapSearchObject));
                        stringBuilder.append("&");
                    }
                } catch (IllegalAccessException e) {
                    log.info("方法{}发生异常:{}", "setUpRequest", e.getMessage());
                }

            }
        }
        return stringBuilder.substring(0, stringBuilder.lastIndexOf("&")).trim();
    }

    /**
     * 组装请求前缀
     */
    private static StringBuilder setUpRequestPrefix(Class clazz) {
        StringBuilder stringBuilder = new StringBuilder();
        switch (clazz.getName()) {
            case DETAILS_BAIDU_SEARCHOBJECT_NAME:
                stringBuilder.append(DETAILS_PREFIX);
                break;
            case CIRCULAR_BAIDU_SEARCHOBJECT_NAME:
                stringBuilder.append(CIRCULAR_PREFIX);
                break;
            case PLACE_BAIDU_SEARCHOBJECT_NAME:
                stringBuilder.append(PLACE_PREFIX);
                break;
            case RECTANGULAR_BAIDU_SEARCHOBJECT_NAME:
                stringBuilder.append(RECTANGULAR_PREFIX);
                break;
        }
        return stringBuilder;
    }

    /**
     * 进行请求
     */
    private static String doRequest(String requestUri) {
        return HttpRequest.get(requestUri).body();
    }
}
