package com.wanghaotian.example.utils.mapsearch.gaode;

import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
@Data
public class PoiGaodeMapSearchObj extends BaseGaodeMapSearchObj{
    private String keywords;//查询关键字
    private String types;//查询POI类型
    private String city;//查询城市,可选值：城市中文、中文全拼、citycode、adcode 如：北京/beijing/010/110000填入此参数后，会尽量优先返回此城市数据，但是不一定仅局限此城市结果，若仅需要某个城市数据请调用citylimit参数。如：在深圳市搜天安门，返回北京天安门结果。
    private String citylimit;//可选值：true/false
    private String children;//是否按照层级展示子POI数据,可选值：children=1当为0的时候，子POI都会显示。当为1的时候，子POI会归类到父POI之中。仅在extensions=all的时候生效
    private int offset;//每页记录数据强烈建议不超过25，若超过25可能造成访问报错
    private int page;//当前页数,最大翻页数100
    private EXTENSIONS_ENUM extensions;//此项默认返回基本地址信息；取值为all返回地址信息、附近POI、道路以及道路交叉口信息。

    private PoiGaodeMapSearchObj() {
    }

    @Override
    public String CallBack(String responce) {
        return null;
    }
}
