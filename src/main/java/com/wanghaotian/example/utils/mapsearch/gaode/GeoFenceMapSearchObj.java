package com.wanghaotian.example.utils.mapsearch.gaode;

import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
@Data
public class GeoFenceMapSearchObj extends BaseGaodeMapSearchObj{
    private String name;//围栏名称,字母&数字&汉字,必填,无
    private String center;//圆形围栏中心点,longitude,latitude,圆形围栏必填与points互斥,无
    private String radius;//圆形围栏半径,单位：米。范围0~5000。圆形围栏必填与points互斥,无
    private String points;//多边形围栏坐标点,lon1,lat1;lon2,lat2;lon3,lat3（3<=点个数<=5000）。多边形围栏外接圆半径最大为5000米。多边形围栏必填,无
    private int enable;//围栏监控状态,布尔类型,可选,true
    private String valid_time;//过期日期,围栏有效截止日期，过期后不对此围栏进行维护（围栏数据过期删除）；不能设定创建围栏时间点之前的日期；格式yyyy-MM-dd； 请设置2055年之前的日期,可选,创建时间后90天；
    private String repeat;//一周内围栏监控日期的重复模式,星期缩写列表，用","或“;”隔开。,repeat和fixed_date不能同时缺省或同时为空，二者所指出的监控日期为“或”关系
    private String fixed_date;//指定日期列表,
    private String time;//一天内围栏监控时段
    private String desc;//围栏描述信息,可选,无
    private String alert_condition;//配置触发围栏所需动作,触发动作分号分割,enter;leave（进入、离开触发）


     GeoFenceMapSearchObj() {
    }

    @Override
    public String CallBack(String responce) {
        return null;
    }
}
