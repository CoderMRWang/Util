package com.wanghaotian.example.utils.mapsearch.gaode;

import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
@Data
public class PlaceGaodeMapSearchObj extends BaseGaodeMapSearchObj{
    private String keywords;//查询关键字,规则：只支持单个关键词语搜索关键词支持：行政区名称、citycode、adcode
    private int subdistrict;//子级行政区,0：不返回下级行政区；1：返回下一级行政区；,2：返回下两级行政区；,3：返回下三级行政区；
    private int page=1;//需要第几页数据
    private int offset=20;//最外层返回数据个数
    private EXTENSIONS_ENUM extensions;//返回结果控制,此项控制行政区信息中返回行政区边界坐标点； 可选值：base、all;
    private String filter;//根据区划过滤,按照指定行政区划进行过滤，填入后则只返回该省/直辖市信息需填入adcode，为了保证数据的正确，强烈建议填入此参数

    private PlaceGaodeMapSearchObj() {
    }

    @Override
    public String CallBack(String responce) {
        return null;
    }
}
