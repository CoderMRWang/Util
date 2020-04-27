package com.wanghaotian.example.utils.mapsearch.gaode;

import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
@Data
public class WeatherGaodeInfoMapSearchObj extends BaseGaodeMapSearchObj{
    private EXTENSIONS_ENUM extensions;//气象类型,可选值：base/all,base:返回实况天气,all:返回预报天气
    private WeatherGaodeInfoMapSearchObj() {
    }

    @Override
    public String CallBack(String responce) {
        return null;
    }

}
