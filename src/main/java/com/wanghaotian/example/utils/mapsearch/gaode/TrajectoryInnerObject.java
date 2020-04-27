package com.wanghaotian.example.utils.mapsearch.gaode;


import com.alibaba.fastjson.JSON;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */

public class TrajectoryInnerObject {
    private double x;//"x": 116.449429,经度
    private double y;//"y": 40.014844,维度
    private int sp;//: 4,速度
    private int ag;//"ag": 110,角度
    private long tm;// 1970年到当前的秒数第一个点对应的tm参数为unix时间戳；从第二个点的tm参数开始，tm取值为相对于前一个tm的时间增量，起始时间戳和时间增量均不能为0，需要真实合理的时间。

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
