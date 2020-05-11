package com.wanghaotian.example.utils.mapsearch.baidu;

import com.github.kevinsawicki.http.HttpRequest;
import com.wanghaotian.example.utils.mapsearch.BaseMapSearchObj;
import com.wanghaotian.example.utils.mapsearch.BaseMapSearchUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Nullable;
import javax.imageio.stream.FileImageOutputStream;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.wanghaotian.example.utils.mapsearch.MapSearchUtils.getRequestUrlPara;
import static com.wanghaotian.example.utils.mapsearch.baidu.BaseBaiduMapSearchObj.SEARCH_TYPE_ENUM.*;
import static com.wanghaotian.example.utils.mapsearch.baidu.FieldCheckError.*;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/24
 * @modify By:
 */
@Slf4j
public class BaiduMapSearchUtils implements BaseMapSearchUtils {
    private static final String DETAILS_PREFIX = "http://api.map.baidu.com/place/v2/detail?";
    private static final String CIRCULAR_PREFIX = "http://api.map.baidu.com/place/v2/search?";
    private static final String PLACE_PREFIX = "http://api.map.baidu.com/place/v2/search?";
    private static final String RECTANGULAR_PREFIX = "http://api.map.baidu.com/place/v2/search?";
    private static final String FILTER = "filter";
    private static final String SEARCH_TYPE = "searchType";
    private static final String DETAILS_BAIDU_SEARCHOBJECT_NAME = "com.wanghaotian.example.utils.mapsearch.baidu.DetailsBaiduMapSearchObj";
    private static final String CIRCULAR_BAIDU_SEARCHOBJECT_NAME = "com.wanghaotian.example.utils.mapsearch.baidu.CircularBaiduMapSearchObj";
    private static final String PLACE_BAIDU_SEARCHOBJECT_NAME = "com.wanghaotian.example.utils.mapsearch.baidu.PlaceBaiduMapSearchObj";
    private static final String RECTANGULAR_BAIDU_SEARCHOBJECT_NAME = "com.wanghaotian.example.utils.mapsearch.baidu.RectangularBaiduMapSearchObj";
    private static final String REQUESTS_URL = "http://maponline0.bdimg.com";
//    private static final String[] REQUESTS_URL =
            {
//            "http://maponline0.bdimg.com"
//            ,
//            "http://maponline1.bdimg.com",
//            "http://maponline2.bdimg.com",
//            "http://maponline3.bdimg.com"
    };
    private static final String WEB_RESOURCE = "./src/main/resources/static/js/tiles";

    private AtomicInteger addX=new AtomicInteger();
    private AtomicInteger addY=new AtomicInteger();
    private AtomicInteger addZ=new AtomicInteger();
    private static final int MAX_X=99999;
    private static final int MAX_Y=99999;
    private static final int MAX_Z=20;



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

    @Override
    public String getResult(BaseMapSearchObj baseMapSearchObj, @NotNull Class clazz) {
        StringBuilder errorString = new StringBuilder();
        String responce = null;
        Assert.isTrue(BaseBaiduMapSearchObj.class.equals(clazz.getSuperclass()), "Class must extends BaseMapSearchObj");
        BaseBaiduMapSearchObj baseBaiduMapSearchObject = (BaseBaiduMapSearchObj) baseMapSearchObj;
        if (checkRequiredItems(baseBaiduMapSearchObject, errorString)) {
            StringBuilder request = setUpRequest(baseBaiduMapSearchObject, clazz);
            if (CollectionUtils.isNotEmpty(baseBaiduMapSearchObject.getFilter())) {
                StringBuilder filterBuilder = new StringBuilder();
                checkFilter(baseBaiduMapSearchObject, filterBuilder);
                log.info("after filter string {}", filterBuilder.toString());
                request.append("&" + filterBuilder);
            }
            log.info("{}", request);
            responce = doRequest(request.toString());
            log.info("返回的结果:{}", responce);
        } else {
            log.info("{}", errorString);
        }
        return responce;
    }


    /**
     * 校验查询必填项
     */
    private boolean checkRequiredItems(BaseBaiduMapSearchObj baseBaiduMapSearchObject, StringBuilder stringBuilder) {
        int okCount = 0;
        if (StringUtils.isBlank(baseBaiduMapSearchObject.getQuery())) {
            okCount -= 1;
            stringBuilder.append(FIELD_QUERY_NULL);
        }
        if (StringUtils.isBlank(baseBaiduMapSearchObject.getAk())) {
            okCount -= 1;
            stringBuilder.append(FIELD_AK_NULL);
        }
        if (StringUtils.isNotBlank(baseBaiduMapSearchObject.getSn()) && ObjectUtils.isEmpty(baseBaiduMapSearchObject.getTimestamp())) {
            okCount -= 1;
            stringBuilder.append(FIELD_TIMESTAMP_NULL);
        }
        if (CollectionUtils.isNotEmpty(baseBaiduMapSearchObject.getFilter()) && !BaseBaiduMapSearchObj.SCOPE_ENUM.DETILS.equals(baseBaiduMapSearchObject.getScope())){
            okCount -= 1;
            stringBuilder.append(FIELD_SCOPE_ERROE);
        }

        return (okCount == 0) && checkSpicalItem(baseBaiduMapSearchObject, stringBuilder);
    }

    private boolean checkSpicalItem(BaseBaiduMapSearchObj baseBaiduMapSearchObject, StringBuilder stringBuilder) {
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
    private boolean checkPlaceItem(BaseBaiduMapSearchObj baseBaiduMapSearchObject, StringBuilder stringBuilder) {
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
    private boolean checkCircularItem(BaseBaiduMapSearchObj baseBaiduMapSearchObject, StringBuilder stringBuilder) {
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
    private boolean checkRectangleItem(BaseBaiduMapSearchObj baseBaiduMapSearchObject, StringBuilder stringBuilder) {
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
    private boolean checkDetailsItem(BaseBaiduMapSearchObj baseBaiduMapSearchObject, StringBuilder stringBuilder) {
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
    private boolean checkFilter(BaseBaiduMapSearchObj baseBaiduMapSearchObject, @Nullable StringBuilder stringBuilder) {
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
    private StringBuilder setUpRequest(BaseBaiduMapSearchObj baseBaiduMapSearchObject, Class<? extends BaseBaiduMapSearchObj> clazz) {
        StringBuilder stringBuilder = setUpRequestPrefix(clazz);
        List<String> ignoreList = new ArrayList<>();
        ignoreList.add(FILTER);
        ignoreList.add(SEARCH_TYPE);
        stringBuilder.append(getRequestUrlPara(baseBaiduMapSearchObject, clazz, ignoreList));
        return stringBuilder;
    }

    /**
     * 组装请求前缀
     */
    private StringBuilder setUpRequestPrefix(Class<? extends BaseBaiduMapSearchObj> clazz) {
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
            default:
                break;
        }
        return stringBuilder;
    }

    /**
     * 进行请求
     */
    private String doRequest(String requestUri) {
        return HttpRequest.get(requestUri).body();
    }
    /**
     *
     * */
    public void getStaticResourceJob()
    {
        while(addX.get()<99999&&addY.get()<99999&&addZ.get()<20){
        try {
            logic(addX,addY,addZ);
        }catch (Exception e)
        {   log.info("发生异常:",e);
            getStaticResourceJob();
        }
        }
    }

    private void logic(AtomicInteger aX,AtomicInteger aY,AtomicInteger aZ){
        for (aX.get();aX.get()<MAX_X; aX.incrementAndGet())
        {
            for (aY.get();aY.get()<MAX_Y;aY.incrementAndGet())
            {
                for (aZ.get(); aZ.get()<MAX_Z;aZ.incrementAndGet())
                {
                    getStaticResource(aX.get(),aY.get(),aZ.get());
                }
                aZ.set(0);
            }
            aY.set(0);
            aZ.set(0);
        }

    }




    public static void getStaticResource(int x,int y,int z) {
//        for (String url : REQUESTS_URL) {
            String s = REQUESTS_URL + "/tile/?qt=vtile&x=" + x + "&y=" + y + "&z=" + z + "&styles=pl&scaler=2&from=jsapi2_0";
            log.info("请求地址:{}", s);
            HttpRequest.keepAlive(true);
            HttpRequest httpRequest = HttpRequest.get(s);
            InputStream fileString = httpRequest.stream();
            byte[] bytes = null;
            try {
                int length = fileString.available();
                if (length < 221) {
                    return;
                }
                bytes = new byte[length];
                fileString.read(bytes);
                if (isHTML(bytes)) {
                    return;
                }
            } catch (IOException e) {
                log.info("这里有个异常:{}", e);
            }
            File imageFile = new File(WEB_RESOURCE+File.separatorChar + z +File.separatorChar + x + File.separatorChar + y + ".jpg");
            try {
                String fileName=imageFile.getAbsolutePath();
                String dir=fileName.substring(0,fileName.lastIndexOf(File.separatorChar));
                File dirFile=new File(dir);
                dirFile.mkdirs();
                imageFile.createNewFile();
            } catch (IOException e) {
                log.info("创建文件出错{}",e);
            }
            try (
                FileImageOutputStream outputStream = new FileImageOutputStream(imageFile);) {
                outputStream.write(bytes);
            } catch (IOException e) {
                log.info("IO出现异常:{}", e);
            }    log.info("完成");
//            break;
//        }

    }

    private static boolean isHTML(byte[] bytes) {
        String reg = "<html>";
        String result = new String(bytes, StandardCharsets.UTF_8);
        return result.startsWith(reg);
    }


}
