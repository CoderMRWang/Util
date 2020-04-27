package com.wanghaotian.example.utils.mapsearch.gaode;

import lombok.Data;

import java.util.List;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
@Data
public class TrajectoryCorrectionMapSearchObj extends BaseGaodeMapSearchObj{
    //post方法
    private String queryString;
    private List<TrajectoryInnerObject> body;
    private TrajectoryCorrectionMapSearchObj() {
    }

    @Override
    public String CallBack(String responce) {
        return null;
    }
}
