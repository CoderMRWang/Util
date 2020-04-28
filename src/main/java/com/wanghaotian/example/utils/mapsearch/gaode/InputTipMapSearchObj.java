package com.wanghaotian.example.utils.mapsearch.gaode;

import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
@Data
public class InputTipMapSearchObj extends BaseGaodeMapSearchObj {

    private String keywords;//查询关键词,必填,无,
    private String type;//POI分类服务可支持传入多个分类，多个类型剑用“|”分隔可选值：POI分类名称、分类代码此处强烈建议使用分类代码，否则可能会得到不符合预期的结果可选无
    private String location;//坐标,格式：“X,Y”（经度,纬度），不可以包含空格,建议使用location参数，可在此location附近优先返回搜索关键词信息在请求参数city不为空时生效可选无
    private String city;//搜索城市,可选值：citycode、adcode，不支持县级市。如：010/110000,adcode信息可参考城市编码表获取。,填入此参数后，会尽量优先返回此城市数据，但是不一定仅局限此城市结果，若仅需要某个城市数据请调用citylimit参数。,如：在深圳市搜天安门，返回北京天安门结果。,可选,无（默认在全国范围内搜索）,all
    private String datatype;//返回的数据类型,返回公交线路数据类型,可选多种数据类型用“|”分隔，可选值：all-返回所有数据类型、poi-返回POI数据类型、bus-返回公交站点数据类型、busline-
    private boolean citylimit;//仅返回指定城市数据,可选值：true/false可选,false

     InputTipMapSearchObj() {
    }

    @Override
    public String CallBack(String responce) {
        return null;
    }

    public enum DATATYPE_ENUM {
        ALL("all", "返回所有数据类型"), POI("poi", "返回POI数据类型"), BUS("bus", "返回公交站点数据类型"), BUSLINE("busline", "返回公交线路数据类型");

        private String value;
        private String meaning;
        DATATYPE_ENUM(String value, String meaning) {
            this.value = value;
            this.meaning = meaning;
        }
    }
}
