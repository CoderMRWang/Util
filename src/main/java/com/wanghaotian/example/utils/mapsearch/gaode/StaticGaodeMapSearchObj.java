package com.wanghaotian.example.utils.mapsearch.gaode;

import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
@Data
public class StaticGaodeMapSearchObj extends BaseGaodeMapSearchObj {
    private String location;//地图中心点中心点坐标。规则：经度和纬度用","分隔 经纬度小数点后不得超过6位。
    private String zoom;//地图级别地图缩放级别:[1,17]必填
    private String size;//图片宽度*图片高度。最大值为1024*1024可选400*400
    private SCALE_ENUM scale;//普通/高清1:返回普通图；2:调用高清图，图片高度和宽度都增加一倍，zoom也增加一倍（当zoom为最大值时，zoom不再改变）。可选1
    private int markers;//标注,使用规则见markers详细说明，标注最大数10个,可选,无
    private int labels;//标签,使用规则见labels详细说明，标签最大数10个,可选,无
    private int paths;//折线,使用规则见paths详细说明，折线和多边形最大数4个,可选,无
    private TRAFFIC_ENUM traffic;//交通路况标识,底图是否展现实时路况。 可选值： 0，不展现；1，展现。可选0

    private StaticGaodeMapSearchObj() {
    }

    @Override
    public String CallBack(String responce) {
        return null;
    }

    public enum SCALE_ENUM {
        BASIC(1, "普通图"), DETILS(2, "高清图");
        private int value;
        private String meanning;

        SCALE_ENUM(int value, String meanning) {
            this.value = value;
            this.meanning = meanning;
        }
    }

    public enum TRAFFIC_ENUM {
        NO(0, "不展现"), YES(1, "展现");
        private int value;
        private String meaning;

        TRAFFIC_ENUM(int value, String meaning) {
            this.value = value;
            this.meaning = meaning;
        }
    }


}
