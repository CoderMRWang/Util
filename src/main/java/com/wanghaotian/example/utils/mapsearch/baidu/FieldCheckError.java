package com.wanghaotian.example.utils.mapsearch.baidu;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/25
 * @modify By:
 */
public enum FieldCheckError {
    FIELD_QUERY_NULL("query属性不能为空! "),FIELD_AK_NULL("ak属性不能为空! "),FIELD_TIMESTAMP_NULL("timestamp属性不能为空! "),
    FIELD_REGION_NULL("region属性不能为空! "),FIELD_LOCATION_NULL("location属性不能为空! "),
    FIELD_BOUND_NULL("bound属性不能为空! "),FIELD_UID_NULL("uid属性不能为空! "),FIELD_UIDS_NULL("uids属性不能为空! "),
    FIELD_SCOPE_ERROE("filter属性需要scope为DETILS");
    private String error;

    FieldCheckError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error ;
    }
}
