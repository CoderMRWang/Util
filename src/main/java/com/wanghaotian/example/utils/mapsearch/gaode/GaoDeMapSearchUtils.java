package com.wanghaotian.example.utils.mapsearch.gaode;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
public class GaoDeMapSearchUtils{
    private static final String REVERSE_GEOCODING_HTTP_URL= "https://restapi.amap.com/v3/geocode/geo?";//地理/逆地理
    private static final String DIRECTION_URL="https://restapi.amap.com/v3/direction/walking?";//路径规划
    private static final String PLACE_HTTP_URL="https://restapi.amap.com/v3/config/district?";//行政区查询
    private static final String POI_HTTP_URL="https://restapi.amap.com/v3/PlaceMapSearchObj/text?";//poi查询
    private static final String IP_HTTP_URL="https://restapi.amap.com/v3/ip?";//IP地址查询
    private static final String BATCH_HTTP_URL="https://restapi.amap.com/v3/batch?";//批量查询
    private static final String STATIC_HTTP_URL="https://restapi.amap.com/v3/staticmap?";//静态地图
    private static final String CONVERT_HTTP_URL="https://restapi.amap.com/v3/assistant/coordinate/convert?";//坐标转换
    private static final String WEATHER_INFO_HTTP_URL="https://restapi.amap.com/v3/weather/weatherInfo?";//天气信息
    private static final String INPUT_TIP_HTTP_URL="https://restapi.amap.com/v3/assistant/inputtips?";//输入提示
    private static final String RECTANGLE_TRAFFIC_HTTP_URL="https://restapi.amap.com/v3/traffic/status/rectangle?";//矩形交通提示
    private static final String GEO_FENCE_HTTP_URL="https://restapi.amap.com/v4/geofence/meta?";//地图栅栏
    private static final String TRAJECTORY_CORRECTION_HTTP_URL ="https://restapi.amap.com/v4/grasproad/driving?";//轨迹纠偏
}
