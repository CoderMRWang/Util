package com.wanghaotian.example.utils.mapsearch.google;

import com.wanghaotian.example.utils.mapsearch.BaseMapSearchObj;
import com.wanghaotian.example.utils.mapsearch.BaseMapSearchUtils;
import org.springframework.util.Assert;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
public class GoogleMapSearchUtils implements BaseMapSearchUtils {
    @Override
    public String getResult(BaseMapSearchObj baseMapSearchObj, Class clazz) {
        Assert.isTrue(BaseGoogleMapSearchObj.class.equals(clazz.getSuperclass()), "Class must extends BaseMapSearchObj");
        return null;
    }
}
