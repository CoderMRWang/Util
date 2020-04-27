package com.wanghaotian.example.utils.mapsearch;


import com.wanghaotian.example.utils.mapsearch.baidu.BaiduMapSearchUtils;
import com.wanghaotian.example.utils.mapsearch.gaode.GaoDeMapSearchUtils;
import com.wanghaotian.example.utils.mapsearch.google.GoogleMapSearchUtils;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
public class MapSearchUtils {

    public enum OUT_PUT_ENUM{
        JSON("json"),XML("xml");

        OUT_PUT_ENUM(String value) {
            this.value = value;
        }

        private String value;
    }

    private MapSearchUtils() {
    }
    public static BaiduMapSearchUtils getBaiduMapSearchUtils(){
        return new BaiduMapSearchUtils();
    }
    public static GaoDeMapSearchUtils getGaoDeMapSearchUtils(){
        return new GaoDeMapSearchUtils();
    }
    public static GoogleMapSearchUtils getGoogleMapSearchUtils(){
        return new GoogleMapSearchUtils();
    }

}
