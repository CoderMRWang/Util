package com.wanghaotian.example.utils.mapsearch.gaode;

import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
@Data
public class ReverseGeoCodingMapSearchObj extends BaseGaodeMapSearchObj{
   private String address;//结构化地址信息必填规则遵循：国家、省份、城市、区县、城镇、乡村、街道、门牌号码、屋邨、大厦，如：北京市朝阳区阜通东大街6号。如果需要解析多个地址的话，请用"|"进行间隔，并且将 batch 参数设置为 true，最多支持 10 个地址进进行"|"分割形式的请求。必填
   private String city;//指定查询的城市可选输入内容包括：指定城市的中文（如北京）、指定城市的中文全拼（beijing）、citycode（010）、adcode（110000），不支持县级市。当指定城市查询内容为空时，会进行全国范围内的地址转换检索。adcode信息可参考城市编码表获取。 可选无，会进行全国范围内搜索
   private boolean batch;//批量查询控制batch 参数设置为 true 时进行批量查询操作，最多支持 10 个地址进行批量查询。,batch 参数设置为 false 时进行单点查询，此时即使传入多个地址也只返回第一个地址的解析查询结果。可选false
    ReverseGeoCodingMapSearchObj() {
   }
   public String CallBack(String reponce){
       return null;
   }
}
