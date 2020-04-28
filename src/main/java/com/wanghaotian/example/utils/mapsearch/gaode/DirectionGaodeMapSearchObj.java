package com.wanghaotian.example.utils.mapsearch.gaode;

import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
@Data
public class DirectionGaodeMapSearchObj extends BaseGaodeMapSearchObj{
    private String origin;//出发点,规则： lon，lat（经度，纬度）， “,”分割，如117.500244, 40.417801     经纬度小数点不超过6位,必填,无
    private String destination;//目的地,规则： lon，lat（经度，纬度）， “,”分割，如117.500244, 40.417801     经纬度小数点不超过6位,必填,无
     DirectionGaodeMapSearchObj() {
    }

    @Override
    public String CallBack(String responce) {
        return null;
    }
}
