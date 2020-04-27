package com.wanghaotian.example.utils.mapsearch.gaode;

import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
@Data
public class ConvertCoordinatesGaodeMapSearchObj extends BaseGaodeMapSearchObj{
    private ConvertCoordinatesGaodeMapSearchObj() {
    }

    private String locations;//坐标点,经度和纬度用","分割，经度在前，纬度在后，经纬度小数点后不得超过6位。多个坐标对之间用”|”进行分隔最多支持40对坐标。,必填,无
    private COORDSYS_ENUM coordsys;//原坐标系,    可选autonavi
    public enum  COORDSYS_ENUM{
        GPS("gps","GPS"),MAPBAR("mapbar","MAPBAR"),BAIDU("baidu","百度"),AUTONAVI("autonavi","不进行转换");
        private String value;
        private String meaning;

        COORDSYS_ENUM(String value, String meaning) {
            this.value = value;
            this.meaning = meaning;
        }
    }

    @Override
    public String CallBack(String responce) {
        return null;
    }
}
