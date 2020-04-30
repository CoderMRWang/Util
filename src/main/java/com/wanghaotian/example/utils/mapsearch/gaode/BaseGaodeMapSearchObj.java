package com.wanghaotian.example.utils.mapsearch.gaode;

import com.wanghaotian.example.utils.mapsearch.BaseMapSearchObj;
import com.wanghaotian.example.utils.mapsearch.enums.CommonMapSearchEnums;
import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
@Data
abstract class BaseGaodeMapSearchObj implements BaseMapSearchObj {
    private String key;//API KEY
    private String sig;//数字签名,数字签名获取和使用方法,可选,无
    private CommonMapSearchEnums.OUT_PUT_ENUM output;//返回数据格式类型,可选值：JSON，XML可选JSON
    private boolean callBack;

    BaseGaodeMapSearchObj() {
        this.output= CommonMapSearchEnums.OUT_PUT_ENUM.JSON;
    }

    public enum EXTENSIONS_ENUM {
        BASE("base", "返回实况天气"), ALL("all", "返回预报天气");
        private String type;
        private String meaning;

        EXTENSIONS_ENUM(String type, String meaning) {
            this.type = type;
            this.meaning = meaning;
        }
    }

    public abstract String CallBack(String responce);
}
