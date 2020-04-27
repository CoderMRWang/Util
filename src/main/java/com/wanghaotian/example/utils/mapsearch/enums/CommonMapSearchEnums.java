package com.wanghaotian.example.utils.mapsearch.enums;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
public class CommonMapSearchEnums {
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
}
