package com.wanghaotian.example.utils.mapsearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/24
 * @modify By:
 */
@Data
@NoArgsConstructor
public class BaseBaiduMapSearchObject {
    private SEARCH_TYPE_ENUM searchType;//查询类型
    private String query;//关键字,必选
    private String tag;//偏好,可选
    private OUT_PUT_ENUM output;//选择XML或JSON
    private SCOPE_ENUM scope;//详细查询或基础查询
    private final List<Map<INDUSTRY_TYPE_ENUM, SortNameDetail>> filter=new ArrayList<>();//过滤条件
    private CODE_TYPE_ENUM coordType;//坐标类型
    private String retCoordtype;//
    private int pageSize=40;//单次大小
    private int pageNum;//页码
    private String ak;//开发者秘钥
    private String sn;//开发者签名
    private Timestamp timestamp;//填写ak后必填


    BaseBaiduMapSearchObject(SEARCH_TYPE_ENUM searchType) {
        this.searchType = searchType;
        this.output=OUT_PUT_ENUM.JSON;
        this.pageNum=1;
    }

    public enum OUT_PUT_ENUM {
        XML("xml"), JSON("json");
        private String str;

        OUT_PUT_ENUM(String str) {
            this.str = str;
        }

        public String getStr() {
            return str;
        }

        @Override
        public String toString() {
            return str ;
        }
    }

    public enum SCOPE_ENUM {
        BASIC(1, "基础信息"), DETILS(2, "详细信息");
        private int type;
        private String meaning;

        SCOPE_ENUM(int type, String meaning) {
            this.type = type;
            this.meaning = meaning;
        }

        public int getType() {
            return type;
        }

        @Override
        public String toString() {
            return this.type+"";
        }
    }

    public enum INDUSTRY_TYPE_ENUM {
        HOTEL("hotel", 1,"酒店，旅馆"), CATER("cater", 2,"餐饮"), LIFE("life", 3,"生活");

        private String type;
        private int type_key;
        private String meaning;
        INDUSTRY_TYPE_ENUM(String type, int type_key,String meanning) {
            this.type = type;
            this.type_key = type_key;
        }

        public String getType() {
            return type;
        }

        public int getType_key() {
            return type_key;
        }


        @Override
        public String toString() {
            return  type ;
        }
    }

    public enum SORT_NAME_ENUM {
        HOTEL_DEFAULT("default", 1, "默认"),//默认
        HPTEL_PRICE("price", 1, "价格"),//价格
        HOTEL_TOTAL_SOCRE("score", 1, "好评"),//好评
        HOTEL_LEVEL("level", 1, "星级"),//星级
        HOTEL_HEALTH_SCORE("health_score", 1, "卫生"),//卫生
        HOTEL_HOTEL_DISTANCE("distance", 1, "距离"),//距离
        CATER_DEFAULT("default", 2, "默认"),//，默认
        CATER_TASTE_RATING("taste_rating", 2, "口味"),//口味
        CATER_PRICE("price", 2, "价格"),//价格
        CATER_OVERALL_RATING("overall_rating", 2, "好评"),//好评
        CATER_SERVICE_RATING("service_rating", 2, "服务"),//服务
        CATER_DISTANCE("distance", 2, "距离"),//距离，只有圆形检索有效
        LIFE_DEFAULT("default", 3, "默认"),//默认
        LIFE_PRICE("price", 3, "价格"),//价格
        LIFE_OVERALL_RATING("overall_rating", 3, "好评"),//好评
        LIFE_COMMENT_NUM("comment_num", 3, "服务"),//服务
        LIFE_DISTANCE("distance", 3, "距离");//距离
        private String type;
        private int type_key;

        SORT_NAME_ENUM(String type, int type_key, String meaning) {
            this.type = type;
            this.type_key = type_key;
        }

        public String getType() {
            return type;
        }

        public int getType_key() {
            return type_key;
        }

        @Override
        public String toString() {
            return type;
        }
    }

    public enum SORT_RULE_ENUM{
        SORT_RULE("sort_rule","排序规则"),PRICE_SECTION("price_section","价格区间"),GROUPON("groupon","是否有团购"),DISCOUNT("discount","是否打折");
        private String value;
        private String meaning;
        SORT_RULE_ENUM(String value, String meaning) {
            this.value = value;
            this.meaning = meaning;
        }

        @Override
        public String toString() {
            return value;
        }
    }


    public enum SORT_CHOICE_ENUM{
        SORT_HIGHT_2_LOW(0,"高到低"),//高到低
        SORT_LOW_2_HIGHT(1, "低到高"),//低到高
        GROUPON_ON(1, "有团购"),//团购
        GROUPON_OFF(0, "无团购"),//无团购
        DISCOUNT_ON(1, "有折扣"),//有折扣
        DISCOUNT_OFF(0, "无折扣");//无折扣
        private int value;
        private String meaning;

        SORT_CHOICE_ENUM(int value, String meaning) {
            this.value = value;
            this.meaning = meaning;
        }

        @Override
        public String toString() {
            return value+"";
        }
    }

    public enum CODE_TYPE_ENUM {
        WGS8411(1, "wgs84ll即GPS经纬度"), GCJ02ll(2, "gcj02ll即国测局经纬度坐标"),
        BD0911(3, "bd09ll即百度经纬度坐标"), BD09MC(4, "bd09mc即百度米制坐标");
        private int type;
        private String meaning;

        CODE_TYPE_ENUM(int type, String meaning) {
            this.type = type;
            this.meaning = meaning;
        }

        public int getType() {
            return type;
        }

        @Override
        public String toString() {
            return type+"";
        }
    }

    public enum SEARCH_TYPE_ENUM {
        PLACE(1), CIRCULAR(2), RECTANGULAR(3), DETAILS(4);
        int type;
        SEARCH_TYPE_ENUM(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type+"";
        }
    }

}
